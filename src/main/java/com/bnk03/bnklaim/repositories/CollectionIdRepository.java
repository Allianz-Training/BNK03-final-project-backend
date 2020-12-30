package com.bnk03.bnklaim.repositories;

import com.bnk03.bnklaim.entity.CollectionId;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollectionIdRepository extends MongoRepository<CollectionId, String> {

}
