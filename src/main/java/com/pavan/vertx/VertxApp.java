/**
 * 
 */
package com.pavan.vertx;

import io.vertx.core.Vertx;

/**
 * @author pk015603
 *
 */
public class VertxApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new MyVerticle());
	}

}
