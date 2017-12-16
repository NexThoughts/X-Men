package com.teamx

import io.vertx.core.Vertx

class VertxMain {

    public static void main(String[] args) {
        def vertx = Vertx.vertx([
                workerPoolSize: 40
        ])
//        new DatabaseVerticle().createTables()
//        vertx.deployVerticle(new ApplicationVerticle())
//        vertx.deployVerticle(new MailVerticle())
//        vertx.deployVerticle(new TemplateTest())
    }

}
