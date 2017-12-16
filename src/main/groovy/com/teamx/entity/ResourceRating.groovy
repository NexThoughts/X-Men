package com.teamx.entity

class ResourceRating {

    Date dateCreated
    String uuid = UUID.randomUUID()
    String resourceUuid
    String userUuid
    Integer score

}
