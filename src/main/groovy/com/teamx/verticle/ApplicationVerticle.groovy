package com.teamx.verticle

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.http.HttpServer
import io.vertx.ext.auth.User
import io.vertx.ext.web.Router

class ApplicationVerticle extends AbstractVerticle {

    private Future<Void> startHttpServer(Future<Void> future) {
        println "---startHttpServer 1 ----"
        HttpServer server = vertx.createHttpServer()
        def router = Router.router(vertx)
        router.route().handler({ routingContext ->
            def response = routingContext.response()
            response.putHeader("content-type", "text/plain")
            response.end("Hello World from Vert.x-Web!")
        })
        server.requestHandler(router.&accept).listen(8085)
        println "Browse to http://127.0.0.1:8085"
        return future;
    }

    void start(Future<Void> startFuture) throws Exception {
        startHttpServer(startFuture).setHandler({ ar ->
            println "--- Inside Start Server ------3------"
            if (ar.succeeded()) {
                startFuture.complete();
                println "--- Inside Start Server ------4------"
            } else {
                startFuture.fail(ar.cause());
                println "--- Inside Start Server ------5------"
            }
        })
    }

}
