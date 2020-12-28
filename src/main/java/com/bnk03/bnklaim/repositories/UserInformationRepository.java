package com.bnk03.bnklaim.repositories;

import com.bnk03.bnklaim.entity.UserInformation;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInformationRepository extends MongoRepository<UserInformation, String> {
    public UserInformation findByInsuranceAccountNumber(String insuranceAccountNumber);

    public UserInformation findByEmail(String email);
}
