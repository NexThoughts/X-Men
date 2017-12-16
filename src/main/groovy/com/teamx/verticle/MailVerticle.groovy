package com.teamx.verticle

import com.teamx.Constants
import io.vertx.core.AbstractVerticle
import io.vertx.ext.mail.MailClient
import io.vertx.ext.mail.MailConfig
import io.vertx.ext.mail.MailMessage
import io.vertx.ext.mail.StartTLSOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

class MailVerticle extends AbstractVerticle {

    void start() {
        println("verticle started")
        Router router = Router.router(vertx)
        router.get("/").handler({ RoutingContext ctx ->
            def response = ctx.response()
            response.putHeader("content-type", "text/plain")
            response.end("try Sending email")
        })
//        router.get("/sendEmail").handler({ RoutingContext ctx ->
//            //Sending Email
//            def response = ctx.response()
//            response.putHeader("content-type", "text/plain")
//            MailConfig config = new MailConfig();
//            config.port = Constants.MAIL_PORT
//            config.hostname = Constants.MAIL_HOSTNAME
//            config.starttls = StartTLSOptions.REQUIRED
//            config.username = Constants.MAIL_USERNAME
//            config.password = Constants.MAIL_PASSWORD
//            MailMessage message = new MailMessage()
//            message.from = Constants.MAIL_FROM
//            message.to = ["abhilash@nexthoughts.com", "hiten@fintechlabs.in"]
//            message.cc = []
//            message.bcc = []
//            message.html = "this is html text <a href=\"http://vertx.io\">vertx.io</a>"
//            message.bounceAddress = Constants.MAIL_BOUNCEADDRESS
//            message.subject = "first mail"
//            MailClient mailClient = MailClient.createShared(vertx, config, "exampleclient");
//            println("+++++++++++++++++++Sening Email+++++++++++++++++++++++++")
//            mailClient.sendMail(message, { result ->
//                if (result.succeeded()) {
//                    println(result.result())
//                    response.end("mail Sent")
//                    println("+++++++++++++++++++Mail sent+++++++++++++++++++")
//                } else {
//                    println("+++++++++++++++++++got exception in email+++++++++++++++++++")
//                    response.end("mail Could not be sent")
//                    result.cause().printStackTrace()
//                }
//            })
//        })

        router.post("/sendEmail").handler(this.&handleSendEmail)
        println("mail verticle running at 8086")
        vertx.createHttpServer().requestHandler(router.&accept).listen(8086)
    }

    void handleSendEmail(RoutingContext ctx){
        //Sending Email
        def response = ctx.response()
        response.putHeader("content-type", "text/plain")
        MailConfig config = new MailConfig();
        config.port = Constants.MAIL_PORT
        config.hostname = Constants.MAIL_HOSTNAME
        config.starttls = StartTLSOptions.REQUIRED
        config.username = Constants.MAIL_USERNAME
        config.password = Constants.MAIL_PASSWORD
        MailMessage message = new MailMessage()
        message.from = Constants.MAIL_FROM
        message.to = ["harish@nexthoughts.com", "manish@fintechlabs.in"]
        message.cc = ["abhilash@nexthoughts.com", "hiten@fintechlabs.in"]
        message.bcc = []
        message.html = "You have been invited"
        message.bounceAddress = Constants.MAIL_BOUNCEADDRESS
        message.subject = "Linkshare Invitation"
        MailClient mailClient = MailClient.createShared(vertx, config, "exampleclient");
        println("+++++++++++++++++++Sening Email+++++++++++++++++++++++++")
        mailClient.sendMail(message, { result ->
            if (result.succeeded()) {
                println(result.result())
                response.end("mail Sent")
                println("+++++++++++++++++++Mail sent+++++++++++++++++++")
            } else {
                println("+++++++++++++++++++got exception in email+++++++++++++++++++")
                response.end("mail Could not be sent")
                result.cause().printStackTrace()
            }
        })
    }
}
