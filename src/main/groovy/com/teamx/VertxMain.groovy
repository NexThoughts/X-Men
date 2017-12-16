package com.teamx

import com.teamx.verticle.ApplicationVerticle
import io.vertx.core.Vertx

class VertxMain {

    public static void main(String[] args) {
        def vertx = Vertx.vertx([
                workerPoolSize: 40
        ])
        vertx.deployVerticle(new ApplicationVerticle())

    }

}
