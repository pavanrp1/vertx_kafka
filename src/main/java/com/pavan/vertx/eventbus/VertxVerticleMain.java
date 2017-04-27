/**
 * 
 */
package com.pavan.vertx.eventbus;

import com.pavan.vertx.MyVerticle;

import io.vertx.core.Vertx;

/**
 * @author pk015603
 *
 */
public class VertxVerticleMain {
	public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new MyVerticle());
        
        vertx.deployVerticle(new EventBusReceiverVerticle("R1"));
        vertx.deployVerticle(new EventBusReceiverVerticle("R2"));

        Thread.sleep(3000);
        vertx.deployVerticle(new EventBusSenderVerticle());
        
    }

}
