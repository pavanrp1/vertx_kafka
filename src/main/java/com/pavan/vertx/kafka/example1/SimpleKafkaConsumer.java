/**
 * 
 */
package com.pavan.vertx.kafka.example1;


import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.pavan.vertx.kafka.example.ConfigConstants;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.kafka.client.consumer.KafkaConsumer;

/**
 * @author pk015603
 *
 */
public class SimpleKafkaConsumer extends AbstractVerticle{


	// use consumer for interacting with Apache Kafka
	KafkaConsumer<String,String> consumer = KafkaConsumer.create(vertx, getConfig());
	
//	@Override
//    public void start(final Future<Void> startedResult) {
//	try {
//	
//		consumer.handler(record -> {
//			  System.out.println("Processing key=" + record.key() + ",value=" + record.value() +
//			    ",partition=" + record.partition() + ",offset=" + record.offset());
//			});
//	} catch (Exception ex) {
////        String error = "Failed to startup";
////        logger.error(error, ex);
////        bus.send(ConfigConstants.CONSUMER_ERROR_TOPIC, getErrorString("Failed to startup", ex.getMessage()));
//	}
	

	private Properties getConfig()
	{
		Properties config = new Properties();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "my_group");
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		return config;
	}
}
