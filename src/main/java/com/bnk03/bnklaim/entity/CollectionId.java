package com.bnk03.bnklaim.entity;

import com.bnk03.bnklaim.utility.ObjectToJson;

import org.springframework.data.annotation.Id;

public class CollectionId {
    @Id
    private String idName;
    private int currentCollectionId;

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public int getCurrentCollectionId() {
        return currentCollectionId;
    }

    public void setCurrentCollectionId(int currentCollectionId) {
        this.currentCollectionId = currentCollectionId;
    }

    @Override
    public String toString() {
        return ObjectToJson.toJsonString(this);
    }
}