package com.teamx

import io.vertx.core.Handler
import io.vertx.core.Vertx

class VertxMain {

    public static void main(String[] args) {
        def vertx = Vertx.vertx([
                workerPoolSize: 40
        ])
//        long timerID = vertx.setTimer(3000, new Handler<Long>() {
        long timerID = vertx.setPeriodic(3000, new Handler<Long>() {
            @Override
            public void handle(Long aLong) {
                System.out.println("Timer 1 fired: " + aLong);
            }
        })
//        vertx.cancelTimer(timerID)

//        DatabaseUtil.createTables()
//        vertx.deployVerticle(new ApplicationVerticle())
//        vertx.deployVerticle(new MailVerticle())
//        vertx.deployVerticle(new TemplateTest())
//        vertx.deployVerticle(new UserVerticle())
//        vertx.deployVerticle(new TemplateTest())
//        vertx.deployVerticle(new ResourceVerticle())
    }

}
