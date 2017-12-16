package com.teamx.verticle

import com.teamx.entity.ResourceLink
import groovy.json.JsonSlurper
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.http.HttpMethod
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.JsonObject
import io.vertx.ext.jdbc.JDBCClient
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.Session
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.CookieHandler
import io.vertx.ext.web.handler.CorsHandler
import io.vertx.ext.web.handler.SessionHandler
import io.vertx.ext.web.sstore.LocalSessionStore

class ResourceVerticle extends AbstractVerticle {

    public static final String API_GET = "/resource/:resourceId";
    public static final String API_LIST_ALL = "/resource";
    public static final String API_CREATE = "/resource";
    public static final String API_UPDATE = "/resource/:todoId";
    public static final String API_DELETE = "/resource/:todoId";
    public static final String API_DELETE_ALL = "/resource";

    void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx)

        Set<String> allowHeaders = new HashSet<>()
        allowHeaders.add("x-requested-with")
        allowHeaders.add("Access-Control-Allow-Origin")
        allowHeaders.add("origin")
        allowHeaders.add("Content-Type")
        allowHeaders.add("accept")
        Set<HttpMethod> allowMethods = new HashSet<>()
        allowMethods.add(HttpMethod.GET)
        allowMethods.add(HttpMethod.POST)
        allowMethods.add(HttpMethod.DELETE)
        allowMethods.add(HttpMethod.PUT)

        router.route().handler(CorsHandler.create("*")
                .allowedHeaders(allowHeaders)
                .allowedMethods(allowMethods))

        router.route().handler(BodyHandler.create())

        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        router.get(API_GET).handler(this.&getResource);
        router.get(API_LIST_ALL).handler(this.&listResource);
        router.post(API_CREATE).handler(this.&createResource);
        router.delete(API_DELETE).handler(this.&deleteResource);
//        router.delete(API_DELETE_ALL).handler(this.&deleteResource)
//        router.delete(API_UPDATE).handler(this.&deleteResource)

        vertx.createHttpServer().requestHandler(router.&accept).listen(8099);
    }

    public static void createResource(RoutingContext routingContext) {
        JsonObject config = new JsonObject()
                .put("url", "jdbc:mysql://localhost:3306/link_sharing?autoreconnect=true")
                .put("user", "root")
                .put("password", "nextdefault")
                .put("driver_class", "com.mysql.jdbc.Driver")
                .put("max_pool_size", 30)
        def client = JDBCClient.createShared(routingContext.vertx(), config)

        Session session = routingContext.session()

        JsonObject resourceJSONData = routingContext.getBodyAsJson()
        def jsonSlurper = new JsonSlurper()
        def resourceObjData = jsonSlurper.parseText(resourceJSONData.toString())
        session.put("currentUserUuid", UUID.randomUUID())
        ResourceLink resourceLink = new ResourceLink(resourceObjData, session.get("currentUserUuid").toString())

        client.getConnection({ conn ->
            def connection = conn.result()
            connection.query("INSERT INTO resource(description, created_by_user_uuid, topic_uuid, date_created, uuid, resource_url) " +
                    "VALUES('${resourceLink.description}','${resourceLink.createByUserUuid}','${resourceLink.topicUuid}','${new java.sql.Date(resourceLink.dateCreated.getTime())}','${resourceLink.uuid}','${resourceLink.resourceUrl}')", { res ->
                if (res.failed()) {
                    println "----10----------"
                    println("Cannot store the data to for ResourceLink the database")
                    res.cause().printStackTrace()
                    return
                }
            })

            connection.close({ done ->
                if (done.failed()) {
                    throw new RuntimeException(done.cause())
                }
            })
            println "Data has been saved successfully for ResourceLink"
            routingContext.response().putHeader("content-type", "application/json").end("SUCCESS")
        })
    }

    public static void getResource(RoutingContext routingContext) {}

    public static void listResource(RoutingContext routingContext) {}

    public static void deleteResource(RoutingContext routingContext) {}

    public static void sendError(int statusCode, HttpServerResponse response) {
        response.setStatusCode(statusCode).end();
    }

}
