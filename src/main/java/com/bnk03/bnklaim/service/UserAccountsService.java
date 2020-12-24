package com.bnk03.bnklaim.service;

import java.util.List;
import java.util.Optional;

import com.bnk03.bnklaim.entities.UserAccounts;
import com.bnk03.bnklaim.repositories.UserAccountsRepository;
import com.jayway.jsonpath.Option;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountsService {
    private UserAccountsRepository userAccountsRepository;

    @Autowired
    public UserAccountsService(UserAccountsRepository userAccountsRepository) {
        this.userAccountsRepository = userAccountsRepository;
    }

    // public UserAccounts register() {

    // }

    public Object retriveUserAccount(String userId) {
        Optional<UserAccounts> opt = userAccountsRepository.findById(userId);
        if (opt.isPresent()) {
            return opt.get();
        }
        return new Object();
    }

    public List<UserAccounts> retriveUserAccounts() {
        return userAccountsRepository.findAll();
    }

}
