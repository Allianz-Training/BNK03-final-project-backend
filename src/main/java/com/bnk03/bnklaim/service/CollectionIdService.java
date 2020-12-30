package com.bnk03.bnklaim.service;

import java.util.Optional;

import com.bnk03.bnklaim.entity.CollectionId;
import com.bnk03.bnklaim.exception.DataNotFoundException;
import com.bnk03.bnklaim.repositories.CollectionIdRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionIdService {

    @Autowired
    private CollectionIdRepository collectionIdRepository;

    public CollectionIdService() {
        // constructor
    }

    public CollectionId getCollection(String collectionName) throws DataNotFoundException {
        Optional<CollectionId> collection = collectionIdRepository.findById(collectionName);
        if (collection.isPresent()) {
            return collection.get();
        } else {
            throw new DataNotFoundException("Collection name not found");
        }
    }

    public Integer increaseCollectionId(String collectionName, int amount) throws DataNotFoundException {
        try {
            CollectionId collection = getCollection(collectionName);
            Integer newId = collection.getCurrentCollectionId() + amount;
            collection.setCurrentCollectionId(newId);
            collectionIdRepository.save(collection);
            return newId;
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException("Collection name not found");
        }
    }
}
