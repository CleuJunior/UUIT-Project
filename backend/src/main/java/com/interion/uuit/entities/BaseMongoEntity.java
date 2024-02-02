package com.interion.uuit.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Data
public abstract class BaseMongoEntity {

    protected ObjectId id;
    @CreatedDate
    protected LocalDateTime creationDate;
    @LastModifiedDate
    protected LocalDateTime updateDate;

    protected BaseMongoEntity() {
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    protected BaseMongoEntity(ObjectId id) {
        this.id = id;
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }
}
