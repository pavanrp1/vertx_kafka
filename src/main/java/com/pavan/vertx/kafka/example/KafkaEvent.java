/**
 * 
 */
package com.pavan.vertx.kafka.example;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import io.vertx.core.json.JsonObject;

/**
 * @author pk015603
 *
 */
public class KafkaEvent {
	public final String topic;
    public final String key;
    public final String value;
    public final int partition;

    public KafkaEvent(JsonObject event) {
        topic = event.getString(ConfigConstants.TOPIC_FIELD);
        key = event.getString(ConfigConstants.KEY_FIELD);
        value = event.getString(ConfigConstants.VALUE_FIELD);
        partition = event.getInteger(ConfigConstants.PARTITION_FIELD);
    }

    @Override
    public String toString() {
        return "KafkaEvent{" +
                "topic='" + topic + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", partition=" + partition +
                '}';
    }

    /**
     * Convert a Kafka ConsumerRecord into an event bus event.
     *
     * @param record the Kafka record
     * @return the record to send over the event bus
     */
    public static JsonObject createEventForBus(ConsumerRecord<String,String> record) {
        return new JsonObject()
                .put(ConfigConstants.TOPIC_FIELD, record.topic())
                .put(ConfigConstants.KEY_FIELD, record.key())
                .put(ConfigConstants.VALUE_FIELD, record.value())
                .put(ConfigConstants.PARTITION_FIELD, record.partition());
    }
}
