/**
 * 
 */
package com.pavan.vertx.com.pavan.vertx.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.pavan.vertx.kafka.example.ConfigConstants;
import com.pavan.vertx.kafka.example.KafkaPublisher;
import com.pavan.vertx.kafka.example.MessageProducer;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

/**
 * @author pk015603
 *
 */
@RunWith(VertxUnitRunner.class)
public class MessageProducerTest {
    private static final Logger logger = LoggerFactory.getLogger(MessageProducerTest.class);

    private static Vertx vertx;

//    @Ignore("This is an integration test comment out to actually run it")
    @Test
    public void testMessageSend(TestContext testContext) {
        Async async = testContext.async();
        vertx = Vertx.vertx();

        JsonObject producerConfig = new JsonObject();
        producerConfig.put(ConfigConstants.BOOTSTRAP_SERVERS, "localhost:9092");
        producerConfig.put(ConfigConstants.DEFAULT_SERIALIZER_CLASS, "org.apache.kafka.common.serialization.StringSerializer");
//        producerConfig.put(ConfigConstants.MAX_BLOCK_MS, new Long(500));
        producerConfig.put("default.topic", "testGroup");

        KafkaPublisher publisher = new KafkaPublisher(vertx.eventBus());

        vertx.deployVerticle(MessageProducer.class.getName(),
        new DeploymentOptions().setConfig(producerConfig), deploy -> {
            if (deploy.failed()) {
                logger.error("", deploy.cause());
                testContext.fail("Could not deploy verticle");
                async.complete();
                vertx.close();
            } else {
//                publisher.send("This is the message you should see in your consumer");
//                // send to a specific topic
//                publisher.send("testGroup", "a test message on a default topic");
//                // send to a specific topic with custom key
//                publisher.send("testGroup", "aUserId", "a test message on a default topic");
//                // send to a specific topic and partition
//                publisher.send("testGroup", "", 5, "a test message on a default topic");
//                // give vert.x some time to get the message off
//                long timerId = vertx.setTimer(50000, timer -> {
//                    async.complete();
//                    vertx.close();
//                });
                
             // Publish the metircs in Kafka
            	int i= 0;
        	    vertx.setPeriodic(1000, id -> {
        	    	 publisher.send("This is the message you should see in your consumer"+i);
        	    });

                vertx.eventBus().consumer(ConfigConstants.PRODUCER_ERROR_TOPIC, reply -> {
                    testContext.fail("test has failed");
                    logger.error("error: " + reply.toString());
//                    vertx.cancelTimer(timerId);
                    vertx.close();
                });
            }
        });
    }
}
