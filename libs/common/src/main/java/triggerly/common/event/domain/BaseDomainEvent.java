package triggerly.common.event.domain;

import java.time.Instant;
import java.util.UUID;

/**
 * Base class for all domain events.
 * Contains minimal metadata required for event-driven systems.
 */
public abstract class BaseDomainEvent {

    private final UUID eventId;
    private final Instant occurredAt;

    protected BaseDomainEvent() {
        this.eventId = UUID.randomUUID();
        this.occurredAt = Instant.now();
    }

    public UUID getEventId() {
        return eventId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }
}
