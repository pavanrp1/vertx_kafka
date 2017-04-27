/**
 * 
 */
package com.pavan.vertx.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;

/**
 * @author pk015603
 *
 */
public class VertxHttpClientVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        HttpClient httpClient = vertx.createHttpClient();
        httpClient.getNow(9999, "localhost", "/", new Handler<HttpClientResponse>() {

            @Override
            public void handle(HttpClientResponse httpClientResponse) {
                System.out.println("Response received");
            }
        });
    }
}
