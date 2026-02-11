package triggerly.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.UUID;

public record Envelope<T>(
        @JsonProperty("eventId") UUID eventId,
        @JsonProperty("occurredAt") Instant occurredAt,
        @JsonProperty("correlationId") String correlationId,
        @JsonProperty("schemaVersion") int schemaVersion, @JsonProperty("producer") String producer,
        @JsonProperty("type") String type, @JsonProperty("payload") T payload
) {

    public Envelope(
            UUID eventId,
            Instant occurredAt,
            String correlationId,
            int schemaVersion,
            String producer,
            String type,
            T payload
    ) {
        this.eventId = eventId;
        this.occurredAt = occurredAt;
        this.correlationId = correlationId;
        this.schemaVersion = schemaVersion;
        this.producer = producer;
        this.type = type;
        this.payload = payload;
    }

    @Override
    public UUID eventId() {
        return eventId;
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }

    @Override
    public String correlationId() {
        return correlationId;
    }

    @Override
    public int schemaVersion() {
        return schemaVersion;
    }

    @Override
    public String producer() {
        return producer;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public T payload() {
        return payload;
    }

    /**
     * Convenience factory for creating events consistently.
     */
    public static <T> Envelope<T> of(String producer, String type, int schemaVersion, String correlationId, T payload) {
        return new Envelope<>(
                UUID.randomUUID(),
                Instant.now(),
                correlationId,
                schemaVersion,
                producer,
                type,
                payload
        );
    }
}
