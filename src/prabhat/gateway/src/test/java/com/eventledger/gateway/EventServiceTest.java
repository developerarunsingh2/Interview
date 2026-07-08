package com.eventledger.gateway;

import com.eventledger.gateway.domain.EventRequest;
import com.eventledger.gateway.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @org.springframework.boot.test.mock.mockito.MockBean
    private com.eventledger.gateway.service.AccountServiceClient accountServiceClient;

    @Test
    public void testEventValidation() {
        EventRequest request = new EventRequest();
        request.setEventId("");
        request.setAmount(100.0);

        assertThrows(IllegalArgumentException.class, () -> {
            eventService.submitEvent(request, "trace-001");
        });
    }

    @Test
    public void testAmountValidation() {
        EventRequest request = new EventRequest();
        request.setEventId("evt-001");
        request.setAccountId("acct-123");
        request.setAmount(-50.0);

        assertThrows(IllegalArgumentException.class, () -> {
            eventService.submitEvent(request, "trace-001");
        });
    }

    @Test
    public void testTypeValidation() {
        EventRequest request = new EventRequest();
        request.setEventId("evt-001");
        request.setAccountId("acct-123");
        request.setAmount(100.0);
        request.setType("INVALID");

        assertThrows(IllegalArgumentException.class, () -> {
            eventService.submitEvent(request, "trace-001");
        });
    }
}
