# Event Ledger System

A distributed event ledger system composed of two microservices: an Event Gateway API (public-facing) and an Account Service (internal). The system handles out-of-order events, idempotency, and graceful degradation when services are unavailable.

## Architecture

```
Browser / Client
    ↓ REST (sync)
Event Gateway API (port 8080)
    ↓ REST (sync)
Account Service (port 8081)
```

### Event Gateway API (Public)
- Entry point for client requests
- Stores event records locally
- Enforces idempotency via eventId
- Calls Account Service to apply transactions
- Features:
  - Circuit breaker pattern for resilience
  - Trace propagation (OpenTelemetry)
  - Structured JSON logging

### Account Service (Internal)
- Manages account state and balances
- Stores transaction history
- Only called by Gateway
- Features:
  - Transaction ordering by eventTimestamp
  - Balance computation (CREDIT - DEBIT)
  - Health checks

## Technology Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.2
- **Database:** H2 (in-memory)
- **Resiliency:** Resilience4j (Circuit Breaker + Retry)
- **Tracing:** OpenTelemetry
- **Testing:** JUnit 5, Spring Test
- **Containerization:** Docker & Docker Compose

## Prerequisites

- Java 17+
- Maven 3.8+
- Docker & Docker Compose (optional, for containerized deployment)

## Setup & Installation

### Local Development

1. Clone the repository:
```bash
git clone <repository-url>
cd event-ledger
```

2. Install dependencies:
```bash
mvn clean install
```

## Running Services

### Option 1: Using Docker Compose (Recommended)

```bash
docker-compose up --build
```

Services will be available at:
- Gateway API: http://localhost:8080
- Account Service: http://localhost:8081

### Option 2: Manual Startup

Terminal 1 - Start Account Service:
```bash
cd account-service
mvn spring-boot:run
```

Terminal 2 - Start Gateway:
```bash
cd gateway
mvn spring-boot:run
```

## API Endpoints

### Event Gateway API (Port 8080)

**Health Check:**
```bash
GET /health
```

**Submit Event:**
```bash
POST /events
Content-Type: application/json

{
  "eventId": "evt-001",
  "accountId": "acct-123",
  "type": "CREDIT",
  "amount": 150.00,
  "currency": "USD",
  "eventTimestamp": "2026-05-15T14:02:11Z",
  "metadata": {
    "source": "mainframe-batch",
    "batchId": "B-9042"
  }
}
```

**Get Event by ID:**
```bash
GET /events/{id}
```

**List Events for Account:**
```bash
GET /events?account={accountId}
```

### Account Service (Port 8081)

**Health Check:**
```bash
GET /health
```

**Get Account Balance:**
```bash
GET /accounts/{accountId}/balance
```

**Get Account Details:**
```bash
GET /accounts/{accountId}
```

**Apply Transaction:**
```bash
POST /accounts/{accountId}/transactions
Content-Type: application/json

{
  "eventId": "evt-001",
  "type": "CREDIT",
  "amount": 150.00,
  "eventTimestamp": "2026-05-15T14:02:11Z"
}
```

## Running Tests

Run all tests:
```bash
mvn test
```

Run specific test suite:
```bash
mvn test -Dtest=EventControllerTest
mvn test -Dtest=AccountServiceIntegrationTest
```

## Core Features

### 1. Idempotency
- Duplicate events are rejected with HTTP 409 Conflict
- Same eventId returns the original event record

### 2. Out-of-Order Tolerance
- Events processed in received order
- Event listings sorted by eventTimestamp
- Balances always correct regardless of arrival sequence

### 3. Balance Computation
- Net balance = Sum(CREDIT) - Sum(DEBIT)
- Computed from all confirmed transactions

### 4. Input Validation
- Required fields: eventId, accountId, type, amount, currency, eventTimestamp
- Amount must be > 0
- Type must be "CREDIT" or "DEBIT"
- ISO 8601 timestamp format required

### 5. Distributed Tracing
- Unique trace ID generated for each Gateway request
- Propagated to Account Service via X-Trace-ID header
- Logged in structured JSON with traceId field

### 6. Resiliency Pattern: Circuit Breaker & Exponential Retry with Jitter
- Implemented via Resilience4j
- Detects repeated Account Service failures
- Opens circuit after 5 consecutive failures
- Half-open state tests recovery with one probe request
- Retry logic features exponential backoff (multiplier 2x) to protect downstream services from retry storms during recovery.

### 7. API Gateway Rate Limiting
- Configured via Resilience4j RateLimiter
- Protects the service from burst loads or DDoS attacks (limited to 100 requests/sec by default).
- Gracefully throttles over-limit clients with HTTP 429 Too Many Requests and returns a trace-correlated JSON error response.

### 8. Asynchronous Fallback (Transactional Outbox Pattern)
- High availability for writes: if the Account Service is offline or the circuit breaker is open, the Gateway continues to accept event submissions by saving them locally as `PENDING` and returning **HTTP 202 Accepted**.
- An asynchronous background worker scans the local DB every 10 seconds to auto-propagate all `PENDING` events to the Account Service once it recovers, ensuring eventual consistency.
- Read queries (`GET /events`, `GET /events/{id}`) continue working from local data during downstream outages.
- Balance queries return HTTP 503 Service Unavailable with a clear error message as they depend on live data.

### 8. Structured Logging
All logs include:
- timestamp (ISO 8601)
- level (INFO, WARN, ERROR)
- service (gateway or account-service)
- traceId
- message
- context data (accountId, amount, etc.)

Example:
```json
{
  "timestamp": "2026-05-15T14:02:11.123Z",
  "level": "INFO",
  "service": "gateway",
  "traceId": "trace-abc123",
  "accountId": "acct-123",
  "message": "Event processed successfully",
  "eventId": "evt-001"
}
```

## Testing Strategy

### Unit Tests
- Event validation
- Balance calculation
- Idempotency logic

### Integration Tests
- Full Gateway → Account Service flow
- Trace propagation
- Out-of-order event processing

### Resilience Tests
- Circuit breaker state transitions
- Timeout + retry behavior
- Error responses during outage

### Example Test Cases
```
✓ Submitting duplicate events returns 409
✓ Events ordered by timestamp in listings
✓ Balance correct after out-of-order arrivals
✓ Circuit breaker opens after repeated failures
✓ Read queries work during Account Service outage
✓ Trace IDs propagate through service calls
✓ Invalid payloads rejected with 400
```

## Configuration

Gateway: `gateway/src/main/resources/application.yml`
- Server port: 8080
- Circuit breaker settings: 5 failure threshold, 30s wait duration
- Timeout: 5 seconds per Account Service call

Account Service: `account-service/src/main/resources/application.yml`
- Server port: 8081
- H2 console: http://localhost:8081/h2-console

## Metrics

Custom metrics logged to stdout:
- Request count by endpoint
- Error rate by service
- Latency percentiles (p50, p95, p99)
- Circuit breaker state changes

## Monitoring

View logs in real-time:
```bash
# Gateway
docker logs -f gateway

# Account Service
docker logs -f account-service
```

Structured logs can be aggregated into Elasticsearch or CloudWatch for centralized monitoring.

## Development Notes

### Adding New Endpoints
1. Define controller method
2. Add integration test
3. Update README API section
4. Ensure trace ID is propagated

### Modifying Balance Logic
- Update `AccountService.calculateBalance()`
- Tests in `BalanceCalculationTest`
- Verify idempotency with duplicate events

### Extending Observability
- OpenTelemetry SDK ready for collector integration
- Configure exporter in `TraceConfiguration`
- Currently exports to logging (console output)

## Troubleshooting

**Gateway can't reach Account Service:**
- Check Account Service is running on 8081
- Verify firewall/network settings
- Check logs for connection errors

**Events not processing:**
- Verify JSON payload matches schema
- Check amount > 0
- Check eventTimestamp is valid ISO 8601

**Circuit breaker stuck open:**
- Wait 30 seconds for half-open recovery attempt
- Or restart services

## Production Readiness & Telemetry

Both services expose full Spring Boot Actuator capabilities:
*   **Health Diagnostics:** `/actuator/health` exposes service status and basic database connectivity.
*   **Prometheus Metrics:** `/actuator/prometheus` provides standardized JVM, thread pool, HTTP traffic, and custom business metrics.
    *   **Gateway Metrics:** `gateway_events_submitted_total` tracks `success`, `pending`, `duplicate`, `error`, and `rate_limited` submissions.
    *   **Account Service Metrics:** `account_transactions_processed_total` tracks `success`, `duplicate`, and `error` outcomes.

## Future Enhancements

- OpenTelemetry Collector + Jaeger/Zipkin visualization integrations.
- Contract tests (Pact) between the two services.

## License

Proprietary - Take-home project evaluation
