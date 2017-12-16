package com.teamx.entity

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

}
