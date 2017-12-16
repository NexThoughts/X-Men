package com.teamx.entity

class ReadingItem {

    Date dateCreated
    String uuid = UUID.randomUUID()
    String resourceUuid
    String userUuid
    Boolean isRead

}
