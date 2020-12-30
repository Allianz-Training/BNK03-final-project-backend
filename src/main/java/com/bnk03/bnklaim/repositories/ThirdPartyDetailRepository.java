package com.bnk03.bnklaim.repositories;

import com.bnk03.bnklaim.entity.ThirdPartyDetail;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThirdPartyDetailRepository extends MongoRepository<ThirdPartyDetail, String> {

}
