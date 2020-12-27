package com.bnk03.bnklaim.repositories;

import com.bnk03.bnklaim.entity.Accounts;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountsRepository extends MongoRepository<Accounts, String> {
    public Accounts findByInsuranceAccountNumber(String insuranceAccountNumber);

    public Accounts findByEmail(String email);
}
