package com.eventledger.gateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.eventledger.gateway.domain.*;
import com.eventledger.gateway.exception.DuplicateEventException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AccountServiceClient accountServiceClient;

    public Event submitEvent(EventRequest request, String traceId) {
        MDC.put("traceId", traceId);
        
        try {
            validateEventRequest(request);

            Optional<Event> existingEvent = eventRepository.findById(request.getEventId());
            if (existingEvent.isPresent()) {
                logger.warn("Duplicate event submission: {}", request.getEventId());
                throw new DuplicateEventException(existingEvent.get());
            }

            Event event;
            try {
                String metadataJson = objectMapper.writeValueAsString(request.getMetadata());
                event = new Event(
                    request.getEventId(),
                    request.getAccountId(),
                    request.getType(),
                    BigDecimal.valueOf(request.getAmount()),
                    request.getCurrency(),
                    Instant.parse(request.getEventTimestamp()),
                    metadataJson,
                    "PENDING"
                );
            } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                logger.error("Failed to serialize metadata for event {}: {}", request.getEventId(), e.getMessage());
                throw new IllegalArgumentException("Invalid metadata", e);
            }

            Event savedEvent = eventRepository.save(event);
            logger.info("Event stored locally: {}", request.getEventId());

            try {
                accountServiceClient.applyTransaction(request.getAccountId(), request, traceId);
                savedEvent.setPropagationStatus("PROPAGATED");
                savedEvent = eventRepository.save(savedEvent);
                logger.info("Transaction applied to account service: {}", request.getEventId());
            } catch (Exception e) {
                logger.warn("Failed to propagate event {} to account service. Queued locally for background retry. Error: {}", 
                    request.getEventId(), e.getMessage());
            }

            return savedEvent;
        } finally {
            MDC.remove("traceId");
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(fixedDelay = 10000)
    public void retryPropagation() {
        List<Event> pendingEvents = eventRepository.findByPropagationStatus("PENDING");
        if (pendingEvents.isEmpty()) {
            return;
        }

        logger.info("Outbox Pattern: Retrying propagation of {} pending events...", pendingEvents.size());

        for (Event event : pendingEvents) {
            String traceId = java.util.UUID.randomUUID().toString();
            MDC.put("traceId", traceId);
            try {
                EventRequest request = new EventRequest();
                request.setEventId(event.getEventId());
                request.setAccountId(event.getAccountId());
                request.setType(event.getType());
                request.setAmount(event.getAmount().doubleValue());
                request.setCurrency(event.getCurrency());
                request.setEventTimestamp(event.getEventTimestamp().toString());

                if (event.getMetadata() != null && !event.getMetadata().isEmpty()) {
                    try {
                        java.util.Map<String, Object> meta = objectMapper.readValue(event.getMetadata(), java.util.Map.class);
                        request.setMetadata(meta);
                    } catch (Exception me) {
                        logger.warn("Failed to deserialize metadata for event retry: {}", me.getMessage());
                    }
                }

                accountServiceClient.propagateDirect(event.getAccountId(), request, traceId);
                event.setPropagationStatus("PROPAGATED");
                eventRepository.save(event);
                logger.info("Successfully propagated pending event: {}", event.getEventId());
            } catch (Exception ex) {
                logger.warn("Retry failed for event {}: {}", event.getEventId(), ex.getMessage());
            } finally {
                MDC.remove("traceId");
            }
        }
    }

    public Optional<Event> getEvent(String eventId) {
        return eventRepository.findById(eventId);
    }

    public List<Event> getEventsByAccount(String accountId) {
        return eventRepository.findByAccountIdOrderByTimestamp(accountId);
    }

    private void validateEventRequest(EventRequest request) {
        if (request.getEventId() == null || request.getEventId().isEmpty()) {
            throw new IllegalArgumentException("eventId is required");
        }
        if (request.getAccountId() == null || request.getAccountId().isEmpty()) {
            throw new IllegalArgumentException("accountId is required");
        }
        if (request.getType() == null || (!request.getType().equals("CREDIT") && !request.getType().equals("DEBIT"))) {
            throw new IllegalArgumentException("type must be CREDIT or DEBIT");
        }
        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new IllegalArgumentException("amount must be greater than 0");
        }
        if (request.getCurrency() == null || request.getCurrency().isEmpty()) {
            throw new IllegalArgumentException("currency is required");
        }
        if (request.getEventTimestamp() == null) {
            throw new IllegalArgumentException("eventTimestamp is required");
        }
        try {
            Instant.parse(request.getEventTimestamp());
        } catch (Exception e) {
            throw new IllegalArgumentException("eventTimestamp must be valid ISO 8601 format");
        }
    }
}
