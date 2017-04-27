package com.pavan.vertx.com.pavan.vertx.http;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.pavan.vertx.http.VertxHttpServerVerticle;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class MyFirstVerticleTest {

  private Vertx vertx;

  @Before
  public void setUp(TestContext context) {
    vertx = Vertx.vertx();
    vertx.deployVerticle(VertxHttpServerVerticle.class.getName(),
        context.asyncAssertSuccess());
  }

  @After
  public void tearDown(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

  @Test
  public void testMyApplication(TestContext context) throws InterruptedException {
    final Async async = context.async();

    vertx.createHttpClient().getNow(9999, "localhost", "/",
     response -> {
      response.handler(body -> {
        context.assertTrue(body.toString().contains("Vert"));
        System.out.println(body);
        async.complete();
      });
    });
    
  }
}