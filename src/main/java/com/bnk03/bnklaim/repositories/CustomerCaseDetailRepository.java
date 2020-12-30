package com.bnk03.bnklaim.repositories;

import com.bnk03.bnklaim.entity.CustomerCaseDetail;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerCaseDetailRepository extends MongoRepository<CustomerCaseDetail, String> {

}
