package com.teamx

import com.teamx.verticle.ApplicationVerticle
import com.teamx.verticle.MailVerticle
import com.teamx.verticle.TemplateTest
import io.vertx.core.Vertx

class VertxMain {

    public static void main(String[] args) {
        def vertx = Vertx.vertx([
                workerPoolSize: 40
        ])
//        vertx.deployVerticle(new ApplicationVerticle())
//        vertx.deployVerticle(new MailVerticle())
        vertx.deployVerticle(new TemplateTest())
    }

}
