package com.bnk03.bnklaim.repositories;

import com.bnk03.bnklaim.entities.UserAccounts;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAccountsRepository extends MongoRepository<UserAccounts, String> {
    public UserAccounts findByEmail(String email);
}
