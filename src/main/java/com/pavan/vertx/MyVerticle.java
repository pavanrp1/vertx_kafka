/**
 * 
 */
package com.pavan.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * @author pk015603
 *
 */
public class MyVerticle extends AbstractVerticle{
	
	@Override
    public void start(Future<Void> startFuture) {
        System.out.println("MyVerticle started!");
        vertx.eventBus().consumer("anAddress", message -> {
            System.out.println("Inside myverticle" + 
                " received message: " +
                message.body());
            message.reply("i got it thanks!");
        });
    }

    @Override
    public void stop(Future stopFuture) throws Exception {
        System.out.println("MyVerticle stopped!");
    }

}
