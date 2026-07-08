package com.eventledger.gateway.controller;

import com.eventledger.gateway.domain.Event;
import com.eventledger.gateway.domain.EventRequest;
import com.eventledger.gateway.domain.ErrorResponse;
import com.eventledger.gateway.exception.DuplicateEventException;
import com.eventledger.gateway.exception.ResourceNotFoundException;
import com.eventledger.gateway.service.EventService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private MeterRegistry meterRegistry;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("{\"status\": \"UP\", \"service\": \"gateway\"}");
    }

    @PostMapping("/events")
    @RateLimiter(name = "submitEventRateLimiter", fallbackMethod = "rateLimiterFallback")
    public ResponseEntity<Event> submitEvent(@RequestBody EventRequest request) {
        String traceId = MDC.get("traceId");
        logger.info("Received event submission: eventId={}", request.getEventId());
        
        String eventType = request.getType() != null ? request.getType() : "UNKNOWN";
        
        try {
            Event event = eventService.submitEvent(request, traceId);
            if ("PENDING".equals(event.getPropagationStatus())) {
                meterRegistry.counter("gateway_events_submitted_total", "status", "pending", "type", eventType).increment();
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(event);
            }
            meterRegistry.counter("gateway_events_submitted_total", "status", "success", "type", eventType).increment();
            return ResponseEntity.status(HttpStatus.CREATED).body(event);
        } catch (DuplicateEventException e) {
            meterRegistry.counter("gateway_events_submitted_total", "status", "duplicate", "type", eventType).increment();
            throw e;
        } catch (Exception e) {
            meterRegistry.counter("gateway_events_submitted_total", "status", "error", "type", eventType).increment();
            throw e;
        }
    }

    public ResponseEntity<ErrorResponse> rateLimiterFallback(EventRequest request, RequestNotPermitted ex) {
        String traceId = MDC.get("traceId");
        logger.warn("Rate limit exceeded for event submission: eventId={}", request.getEventId());
        String eventType = request.getType() != null ? request.getType() : "UNKNOWN";
        meterRegistry.counter("gateway_events_submitted_total", "status", "rate_limited", "type", eventType).increment();
        ErrorResponse response = new ErrorResponse("TOO_MANY_REQUESTS", "Rate limit exceeded. Please try again later.", 429, traceId != null ? traceId : "N/A");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable String id) {
        logger.info("Fetching event: eventId={}", id);
        Event event = eventService.getEvent(id)
            .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + id));
        return ResponseEntity.ok(event);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> listEventsByAccount(@RequestParam String account) {
        logger.info("Listing events for account: {}", account);
        List<Event> events = eventService.getEventsByAccount(account);
        return ResponseEntity.ok(events);
    }
}
