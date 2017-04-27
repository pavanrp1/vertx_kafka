/**
 * 
 */
package com.pavan.vertx.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

/**
 * @author pk015603
 *
 */
public class VertxHttpServerVerticle extends AbstractVerticle {

    private HttpServer httpServer = null;

    @Override
    public void start(Future<Void> future) throws Exception {
        httpServer = vertx.createHttpServer();

        httpServer.requestHandler(new Handler<HttpServerRequest>() {
            @Override
            public void handle(HttpServerRequest request) {
                System.out.println("incoming request!");
                request.uri();
                request.path();
                request.getParam("p1");
                
                //for POST requests
                if(request.method() == HttpMethod.POST){
                    request.handler(new Handler<Buffer>() {
                        @Override
                        public void handle(Buffer buffer) {
                                
                        }
                    });
                }
                
                HttpServerResponse response = request.response();
                response.setStatusCode(200);
                response.headers()
                    .add("Content-Length", String.valueOf(57))
                    .add("Content-Type", "text/html");
                response.write("Vert.x is alive!");
//                response.end();
            }
        });
        httpServer.listen(9999);

//        httpServer.close();
    }
}
