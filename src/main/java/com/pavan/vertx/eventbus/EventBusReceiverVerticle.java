/**
 * 
 */
package com.pavan.vertx.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * @author pk015603
 *
 */
public class EventBusReceiverVerticle extends AbstractVerticle {

	private String name = null;

    public EventBusReceiverVerticle(String name) {
        this.name = name;
    }

    public void start(Future<Void> startFuture) {
        vertx.eventBus().consumer("anAddress", message -> {
            System.out.println(this.name + 
                " received message: " +
                message.body());
            message.reply("i got it thanks!");
        });
    }
}