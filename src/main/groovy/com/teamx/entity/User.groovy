package com.teamx.entity;

class User {

    String userName
    String password
    String firstName
    String lastName
    Boolean isAdmin
    Boolean isActive
    Date dateCreated
    String uuid

    User() {}

    User(String userName, String password, String firstName, String lastName, Boolean isAdmin, Boolean isActive, Date dateCreated) {
        this.userName = userName
        this.password = password
        this.firstName = firstName
        this.lastName = lastName
        this.isAdmin = isAdmin
        this.isActive = isActive
        this.dateCreated = dateCreated
        this.uuid = UUID.randomUUID()
    }

}
