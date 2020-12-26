package com.bnk03.bnklaim.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.bnk03.bnklaim.entities.UserAccounts;
import com.bnk03.bnklaim.repositories.UserAccountsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAccountsService {
    private static final long OTP_VALID_DURATION = 5 * 60 * 1000; // 5 minutes

    private UserAccountsRepository userAccountsRepository;

    @Autowired
    public UserAccountsService(UserAccountsRepository userAccountsRepository) {
        this.userAccountsRepository = userAccountsRepository;
    }

    public boolean isValidUserAccountData(UserAccounts userInput) {
        UserAccounts userAccount = userAccountsRepository.findByEmail(userInput.getEmail());
        if (userAccount == null) {
            return false;
        }
        try {
            return userInput.getFirstName().equals(userAccount.getFirstName())
                    && userInput.getLastName().equals(userAccount.getLastName())
                    && userInput.getEmail().equals(userAccount.getEmail())
                    && userInput.getInsuranceAccountNumber().equals(userAccount.getInsuranceAccountNumber());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRegisteredAccount(UserAccounts userInput) {
        UserAccounts userAccount = userAccountsRepository.findByEmail(userInput.getEmail());
        return userAccount.getIsRegistered();
    }

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
