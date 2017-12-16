package com.teamx

import io.vertx.core.Vertx

class VertxMain {

    public static void main(String[] args) {
        def vertx = Vertx.vertx([
                workerPoolSize: 40
        ])
//        DatabaseUtil.createTables()
//        vertx.deployVerticle(new ApplicationVerticle())
//        vertx.deployVerticle(new MailVerticle())
        vertx.deployVerticle(new TemplateTest())
        vertx.deployVerticle(new UserVerticle())
//        vertx.deployVerticle(new TemplateTest())
//        vertx.deployVerticle(new ResourceVerticle())
    }

}
