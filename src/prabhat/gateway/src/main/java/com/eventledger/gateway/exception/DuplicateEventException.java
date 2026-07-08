package com.eventledger.gateway.exception;

import com.eventledger.gateway.domain.Event;

public class DuplicateEventException extends RuntimeException {
    private final Event originalEvent;

    public DuplicateEventException(Event originalEvent) {
        super("Duplicate event submission: " + originalEvent.getEventId());
        this.originalEvent = originalEvent;
    }

    public Event getOriginalEvent() {
        return originalEvent;
    }
}
