package com.eventledger.gateway;

import com.eventledger.gateway.domain.Event;
import com.eventledger.gateway.domain.EventRequest;
import com.eventledger.gateway.domain.EventRepository;
import com.eventledger.gateway.service.EventService;
import com.eventledger.gateway.service.AccountServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class EventServiceIntegrationTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @MockBean
    private AccountServiceClient accountServiceClient;

    @Test
    public void testIdempotentEventSubmission() {
        EventRequest request = new EventRequest();
        request.setEventId("evt-idempotent-001");
        request.setAccountId("acct-test-001");
        request.setType("CREDIT");
        request.setAmount(100.0);
        request.setCurrency("USD");
        request.setEventTimestamp(Instant.now().toString());
        request.setMetadata(null);

        Event event1 = eventService.submitEvent(request, "trace-001");
        
        com.eventledger.gateway.exception.DuplicateEventException exception = assertThrows(
            com.eventledger.gateway.exception.DuplicateEventException.class,
            () -> eventService.submitEvent(request, "trace-002")
        );

        assertEquals(event1.getEventId(), exception.getOriginalEvent().getEventId());
        assertEquals(1, eventRepository.findByAccountIdOrderByTimestamp("acct-test-001").size());
    }

    @Test
    public void testOutOfOrderEventProcessing() {
        EventRequest event1 = new EventRequest();
        event1.setEventId("evt-order-001");
        event1.setAccountId("acct-order-test");
        event1.setType("CREDIT");
        event1.setAmount(100.0);
        event1.setCurrency("USD");
        event1.setEventTimestamp("2026-05-15T10:00:00Z");
        event1.setMetadata(null);

        EventRequest event2 = new EventRequest();
        event2.setEventId("evt-order-002");
        event2.setAccountId("acct-order-test");
        event2.setType("CREDIT");
        event2.setAmount(50.0);
        event2.setCurrency("USD");
        event2.setEventTimestamp("2026-05-15T09:00:00Z");
        event2.setMetadata(null);

        eventService.submitEvent(event1, "trace-001");
        eventService.submitEvent(event2, "trace-002");

        List<Event> events = eventService.getEventsByAccount("acct-order-test");
        assertEquals(2, events.size());
        assertEquals(Instant.parse("2026-05-15T09:00:00Z"), events.get(0).getEventTimestamp());
        assertEquals(Instant.parse("2026-05-15T10:00:00Z"), events.get(1).getEventTimestamp());
    }

    @Test
    public void testEventRetrieval() {
        EventRequest request = new EventRequest();
        request.setEventId("evt-retrieve-001");
        request.setAccountId("acct-retrieve-test");
        request.setType("DEBIT");
        request.setAmount(75.0);
        request.setCurrency("USD");
        request.setEventTimestamp(Instant.now().toString());
        request.setMetadata(null);

        eventService.submitEvent(request, "trace-001");

        Optional<Event> retrieved = eventService.getEvent("evt-retrieve-001");
        assertTrue(retrieved.isPresent());
        assertEquals("evt-retrieve-001", retrieved.get().getEventId());
        assertEquals("DEBIT", retrieved.get().getType());
    }

    @Test
    public void testEventListingByAccount() {
        EventRequest req1 = new EventRequest();
        req1.setEventId("evt-list-001");
        req1.setAccountId("acct-list-test");
        req1.setType("CREDIT");
        req1.setAmount(100.0);
        req1.setCurrency("USD");
        req1.setEventTimestamp(Instant.now().toString());
        req1.setMetadata(null);

        EventRequest req2 = new EventRequest();
        req2.setEventId("evt-list-002");
        req2.setAccountId("acct-list-test");
        req2.setType("CREDIT");
        req2.setAmount(200.0);
        req2.setCurrency("USD");
        req2.setEventTimestamp(Instant.now().toString());
        req2.setMetadata(null);

        eventService.submitEvent(req1, "trace-001");
        eventService.submitEvent(req2, "trace-002");

        List<Event> events = eventService.getEventsByAccount("acct-list-test");
        assertEquals(2, events.size());
    }
}
