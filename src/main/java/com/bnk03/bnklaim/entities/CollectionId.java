package com.bnk03.bnklaim.entities;

import org.springframework.data.annotation.Id;

public class CollectionId {
    @Id
    private String id;

    private int currentCollectionId;

    public CollectionId() {
        // constructor
    }

    public CollectionId(String id, int currentCollectionId) {
        this.id = id;
        this.currentCollectionId = currentCollectionId;
    }

    public String getId() {
        return id;
    }

    public int getCurrentCollectionId() {
        return currentCollectionId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCurrentCollectionId(int currentCollectionId) {
        this.currentCollectionId = currentCollectionId;
    }
}
