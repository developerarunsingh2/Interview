package com.eventledger.gateway;

import com.eventledger.gateway.domain.EventRequest;
import com.eventledger.gateway.service.AccountServiceClient;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ResiliencyTest {

    @Autowired
    private AccountServiceClient accountServiceClient;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    private RateLimiterRegistry rateLimiterRegistry;

    @BeforeEach
    public void resetCircuitBreaker() {
        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("accountService");
        cb.reset();
    }

    @Test
    public void testRateLimiterIsConfigured() {
        io.github.resilience4j.ratelimiter.RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter("submitEventRateLimiter");
        assertNotNull(rateLimiter);
        assertEquals("submitEventRateLimiter", rateLimiter.getName());
        assertEquals(100, rateLimiter.getRateLimiterConfig().getLimitForPeriod());
    }

    @Test
    public void testRetryBehavior() {
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Void.class)))
                .thenThrow(new org.springframework.web.client.ResourceAccessException("Connection refused"));

        EventRequest request = new EventRequest();
        request.setEventId("evt-retry-test");
        request.setAccountId("acct-123");
        request.setType("CREDIT");
        request.setAmount(100.0);
        request.setCurrency("USD");
        request.setEventTimestamp("2026-05-15T14:02:11Z");

        assertThrows(RuntimeException.class, () -> {
            accountServiceClient.applyTransaction("acct-123", request, "trace-retry");
        });

        // Verify that restTemplate.exchange was called 3 times (due to 3 attempts in retry configuration)
        verify(restTemplate, times(3)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Void.class));
    }

    @Test
    public void testCircuitBreakerOpensAndFastFails() {
        // Stub RestTemplate to fail
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Void.class)))
                .thenThrow(new org.springframework.web.client.ResourceAccessException("Connection refused"));

        EventRequest request = new EventRequest();
        request.setEventId("evt-cb-test");
        request.setAccountId("acct-123");
        request.setType("CREDIT");
        request.setAmount(100.0);
        request.setCurrency("USD");
        request.setEventTimestamp("2026-05-15T14:02:11Z");

        // The sliding window size is 5. We trigger 5 failures.
        for (int i = 0; i < 5; i++) {
            assertThrows(RuntimeException.class, () -> {
                accountServiceClient.applyTransaction("acct-123", request, "trace-cb");
            });
        }

        // Now, the circuit breaker should be OPEN.
        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("accountService");
        assertEquals(CircuitBreaker.State.OPEN, cb.getState());

        // Under OPEN state, the next call should fail immediately without calling RestTemplate.
        reset(restTemplate);

        assertThrows(RuntimeException.class, () -> {
            accountServiceClient.applyTransaction("acct-123", request, "trace-cb-open");
        });

        // RestTemplate should be called 0 times because the circuit breaker is OPEN
        verify(restTemplate, never()).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Void.class));
    }
}
