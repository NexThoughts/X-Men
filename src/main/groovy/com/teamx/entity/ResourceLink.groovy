package com.teamx.entity

import java.text.SimpleDateFormat

class ResourceLink {

    Date dateCreated
    String uuid
    String description
    String createByUserUuid
    String topicUuid
    String resourceUrl

    ResourceLink() {}

    ResourceLink(def jsonData, String createByUserUuid) {
        this.uuid = UUID.randomUUID()
        this.dateCreated = new Date()
        this.description = jsonData.description
        this.resourceUrl = jsonData.resourceUrl
        this.topicUuid = jsonData.topicUuid
        this.createByUserUuid = createByUserUuid
    }

    ResourceLink(def jsonArray) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd")
        this.dateCreated = dateFormat.parse(jsonArray[4])
        this.uuid = jsonArray[5]
        this.description = jsonArray[1]
        this.createByUserUuid = jsonArray[2]
        this.topicUuid = jsonArray[3]
        this.resourceUrl = jsonArray[6]
    }

}
