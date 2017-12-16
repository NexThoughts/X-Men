package com.teamx.util

import com.mysql.jdbc.Driver
import groovy.sql.Sql

import java.sql.DriverManager

class DatabaseUtil {
    static def createConnection() {
        DriverManager.registerDriver(new Driver())
        try {
            def dbURL = 'jdbc:mysql://localhost:3306/link_sharing'
            def dbUserName = 'root'
            def dbPassword = 'nextdefault'
            def dbDriver = 'com.mysql.jdbc.Driver'
            println('Connection Successfull ..!!')
            def sql = Sql.newInstance(dbURL, dbUserName, dbPassword, dbDriver)
            println "Returning sql instance"
            return sql
        } catch (Exception e) {
            println('Error : try again ....!!')
            println(e.getMessage())
        }
    }

    static def createTables() {

        Sql sql = createConnection()

        sql.execute "CREATE TABLE user(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                " user_name  VARCHAR(64) , password  VARCHAR(64) , first_name  VARCHAR(64) ,last_name  VARCHAR(64) ," +
                "is_admin TINYINT(1) , is_active TINYINT(1) ,date_created DATE , uuid VARCHAR(64) ) ;"

        println "################# ----------   Creating Table : user   ------------#################"


        sql.execute "CREATE TABLE topic(id INTEGER PRIMARY KEY AUTO_INCREMENT , name VARCHAR(60) , " +
                "created_by_user_uuid VARCHAR(60) , date_created DATE , visibility TINYINT(1) , uuid  VARCHAR(64)) ;"

        println "################# ----------   Creating Table : topic   ------------#################"



        sql.execute "CREATE TABLE subscription(id INTEGER PRIMARY KEY AUTO_INCREMENT, topic_uuid  VARCHAR(64) , user_uuid VARCHAR(64) ," +
                " seriousness VARCHAR(64) , date_created DATE, uuid VARCHAR(64) ) ;"

        println "################# ----------   Creating Table : subscription   ------------#################"



        sql.execute "CREATE TABLE resource(id INTEGER PRIMARY KEY AUTO_INCREMENT, description  VARCHAR(64) ,created_by_user_uuid VARCHAR(64) , " +
                "topic_uuid VARCHAR(64) , date_created DATE , uuid VARCHAR(64), resourceUrl VARCHAR(64)) ;"

        println "################# ----------   Creating Table : resource   ------------#################"


        sql.execute "CREATE TABLE reading_item(id INTEGER PRIMARY KEY AUTO_INCREMENT, resource_uuid  VARCHAR(64) , user_uuid  VARCHAR(64) , is_read TINYINT(1), uuid VARCHAR(64), date_created DATE) ;"

        println "################# ----------   Creating Table : reading_item   ------------#################"


        sql.execute "CREATE TABLE resource_rating(id INTEGER PRIMARY KEY AUTO_INCREMENT, resource_uuid  VARCHAR(64) , user_uuid  VARCHAR(64)  , score INTEGER, uuid VARCHAR(64), date_created DATE) ;"

        println "################# ----------   Creating Table : resource_rating   ------------#################"


    }

    static def getData(Sql sql) {
        sql.eachRow("SELECT * FROM tablename") { row ->
            println "id : ${row.id}  firstname : ${row.username}"
        }
    }

    static def setData(Sql sql) {
        //sql.execute("INSERT INTO Author(id , firstname) VALUES(${id} , ${firstname})")

    }
}
