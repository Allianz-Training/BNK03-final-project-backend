package com.bnk03.bnklaim.service;

import java.util.Optional;

import com.bnk03.bnklaim.entities.CollectionId;
import com.bnk03.bnklaim.repositories.CollectionIdRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionIdService {
    private CollectionIdRepository nextIdRepository;

    @Autowired
    public CollectionIdService(CollectionIdRepository nextIdRepository) {
        this.nextIdRepository = nextIdRepository;
    }

    public void increaseNextId(String collectionName) {
        CollectionId currentId = findCollectionById(collectionName);
        nextIdRepository.save(new CollectionId(currentId.getId(), currentId.getCurrentCollectionId() + 1));
    }

    public int getCurrentCollectionIdByCollectionName(String collectionName) {
        return findCollectionById(collectionName).getCurrentCollectionId();
    }

    private CollectionId findCollectionById(String collectionName) {
        Optional<CollectionId> opt = nextIdRepository.findById(collectionName);

        if (opt.isPresent()) {
            return opt.get();
        }
        return new CollectionId("none", 0);
    }
}
