package com.eventledger.gateway.service;

import com.eventledger.gateway.domain.EventRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${account.service.url:http://localhost:8081}")
    private String accountServiceUrl;

    @CircuitBreaker(name = "accountService", fallbackMethod = "accountServiceFallback")
    @Retry(name = "accountService")
    public void applyTransaction(String accountId, EventRequest request, String traceId) {
        logger.info("Calling account service for account: {}", accountId);
        
        String url = accountServiceUrl + "/accounts/" + accountId + "/transactions";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Trace-ID", traceId);
        headers.set("Content-Type", "application/json");
        
        HttpEntity<EventRequest> entity = new HttpEntity<>(request, headers);
        
        try {
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
            logger.info("Transaction applied successfully: {} status={}", accountId, response.getStatusCode());
        } catch (Exception e) {
            logger.error("Error applying transaction to account service", e);
            throw new RuntimeException("Failed to apply transaction: " + e.getMessage(), e);
        }
    }

    public void accountServiceFallback(String accountId, EventRequest request, String traceId, Exception ex) {
        logger.error("Circuit breaker activated for account service. Fallback called.", ex);
        throw new RuntimeException("Account Service is unavailable. Please try again later.");
    }

    public void propagateDirect(String accountId, EventRequest request, String traceId) {
        logger.info("Propagating transaction directly (background retry) for account: {}", accountId);
        String url = accountServiceUrl + "/accounts/" + accountId + "/transactions";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Trace-ID", traceId);
        headers.set("Content-Type", "application/json");
        HttpEntity<EventRequest> entity = new HttpEntity<>(request, headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
    }
}
