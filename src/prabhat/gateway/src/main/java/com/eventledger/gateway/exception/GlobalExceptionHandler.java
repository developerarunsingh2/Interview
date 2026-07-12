package com.eventledger.gateway.exception;

import com.eventledger.gateway.domain.ErrorResponse;
import com.eventledger.gateway.domain.Event;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String MDC_TRACE_ID_KEY = "traceId";

    private String getTraceId() {
        String traceId = MDC.get(MDC_TRACE_ID_KEY);
        return traceId != null ? traceId : "N/A";
    }

    @ExceptionHandler(DuplicateEventException.class)
    public ResponseEntity<Event> handleDuplicateEvent(DuplicateEventException ex) {
        logger.warn("Duplicate event submission handled: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getOriginalEvent());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        logger.warn("Resource not found handled: {}", ex.getMessage());
        ErrorResponse response = new ErrorResponse("NOT_FOUND", ex.getMessage(), 404, getTraceId());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        logger.warn("Validation error handled: {}", ex.getMessage());
        ErrorResponse response = new ErrorResponse("VALIDATION_ERROR", ex.getMessage(), 400, getTraceId());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<ErrorResponse> handleCallNotPermitted(CallNotPermittedException ex) {
        logger.error("Circuit breaker is OPEN. Short-circuiting call to account service: {}", ex.getMessage());
        ErrorResponse response = new ErrorResponse("SERVICE_UNAVAILABLE", "Account Service is temporarily unavailable (circuit breaker open).", 503, getTraceId());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        logger.error("Unhandled exception occurred", ex);
        
        // Handle fallback wrapper or direct account service failure messages
        if (ex.getMessage() != null && (ex.getMessage().contains("unavailable") || ex.getMessage().contains("Connection refused"))) {
            ErrorResponse response = new ErrorResponse("SERVICE_UNAVAILABLE", "Account Service is unavailable. Please try again later.", 503, getTraceId());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
        
        ErrorResponse response = new ErrorResponse("INTERNAL_ERROR", "An unexpected error occurred.", 500, getTraceId());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
