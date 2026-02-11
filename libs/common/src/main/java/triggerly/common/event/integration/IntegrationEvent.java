package triggerly.common.event.integration;

/**
 * Marker + metadata contract for integration events (Kafka payloads).
 * These are public contracts between services.
 */
public interface IntegrationEvent {

    /**
     * Logical event type identifier. Usually matches topic name.
     * Example: "market.price.updated.v1"
     */
    String eventType();

    /**
     * Schema version for payload evolution.
     */
    int schemaVersion();

    /**
     * Kafka partition key suggestion (ordering guarantee per key).
     * Example: symbol, userId, alertId
     */
    String partitionKey();
}
