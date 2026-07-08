package com.eventledger.gateway.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "events")
public class Event {
    @Id
    private String eventId;
    private String accountId;
    private String type;
    private BigDecimal amount;
    private String currency;
    private Instant eventTimestamp;
    private Instant recordedAt;
    private String propagationStatus; // "PROPAGATED" or "PENDING"
    
    @Column(columnDefinition = "LONGTEXT")
    private String metadata;

    public Event() {}

    public Event(String eventId, String accountId, String type, BigDecimal amount, 
                 String currency, Instant eventTimestamp, String metadata, String propagationStatus) {
        this.eventId = eventId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.eventTimestamp = eventTimestamp;
        this.metadata = metadata;
        this.recordedAt = Instant.now();
        this.propagationStatus = propagationStatus;
    }

    public Event(String eventId, String accountId, String type, BigDecimal amount, 
                 String currency, Instant eventTimestamp, String metadata) {
        this(eventId, accountId, type, amount, currency, eventTimestamp, metadata, "PROPAGATED");
    }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public Instant getEventTimestamp() { return eventTimestamp; }
    public void setEventTimestamp(Instant eventTimestamp) { this.eventTimestamp = eventTimestamp; }
    public Instant getRecordedAt() { return recordedAt; }
    public void setRecordedAt(Instant recordedAt) { this.recordedAt = recordedAt; }
    public String getMetadata() { return metadata; }
    public void setMetadata(String metadata) { this.metadata = metadata; }
    public String getPropagationStatus() { return propagationStatus; }
    public void setPropagationStatus(String propagationStatus) { this.propagationStatus = propagationStatus; }
}
