package com.teamx.verticle

import com.teamx.util.DatabaseUtil
import groovy.sql.Sql
import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.JsonArray
import io.vertx.ext.sql.SQLConnection
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

class UserVerticle extends AbstractVerticle {
    void start() {
        println("User Verticle Started")
        Router router = Router.router(vertx)
        /*router.get("/").handler({ RoutingContext ctx ->
            def response = ctx.response()
            response.putHeader("content-type", "text/plain")
            response.end("try Creating User")
        })*/
        router.post("/handleAddUser").handler(this.&handleAddUser)
        vertx.createHttpServer().requestHandler(router.&accept).listen(8088)
    }
        void handleAddUser(RoutingContext ctx) {

        HttpServerResponse response = ctx.response()
        ctx.put("title", "Add User")
        println("****************************8")
        def sql = new DatabaseUtil().createConnection()
        sql.execute("INSERT INTO user (first_name, last_name,user_name,password,admin," +
                "active,date_created,uuid) VALUES (?,?,?,?,?,?,?,?)",
                new JsonArray()
                        .add(ctx.request().getFormAttribute("first_name"))
                        .add(ctx.request().getFormAttribute("last_name"))
                        .add(ctx.request().getFormAttribute("user_name"))
                        .add(ctx.request().getFormAttribute("password"))
                        .add(false)
                        .add(new Date())
                        .add(UUID.randomUUID().toString())
                { query ->
                    if (query.failed()) {
                        sendError(500, response)
                    } else {
                        response.end()
                    }
                })

    }

}

