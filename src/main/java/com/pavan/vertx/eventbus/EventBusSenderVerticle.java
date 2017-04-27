/**
 * 
 */
package com.pavan.vertx.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;

/**
 * @author pk015603
 *
 */
public class EventBusSenderVerticle extends AbstractVerticle {

    public void start(Future<Void> startFuture) {
    	DeliveryOptions options = new DeliveryOptions();
    	options.addHeader("some-header", "some-value");
    	vertx.eventBus().send   ("anAddress", "message 1", ar -> {
    		  if (ar.succeeded()) {
    			    System.out.println("Received reply: " + ar.result().body());
    			  }
    			});
        vertx.eventBus().publish("anAddress", "message 2");
        vertx.eventBus().publish   ("anAddress", "message 3",options);
    }
}
