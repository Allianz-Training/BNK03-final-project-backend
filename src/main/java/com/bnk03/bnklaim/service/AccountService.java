package com.bnk03.bnklaim.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Map;
import java.util.TimeZone;
import java.util.Map.Entry;

import com.bnk03.bnklaim.entity.Accounts;
import com.bnk03.bnklaim.repositories.AccountsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountsRepository accountsRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public AccountService() {
        // constructor
    }

    public String bcrypString(String string) {
        return bCryptPasswordEncoder.encode(string);
    }

    public boolean matcher(String string, String encodedString) {
        return bCryptPasswordEncoder.matches(string, encodedString);
    }

    public boolean isUserExistedInDB(Accounts account) {
        Accounts dbAccount = accountsRepository.findByInsuranceAccountNumber(account.getInsuranceAccountNumber());
        if (dbAccount == null) {
            return false;
        }
        try {
            return account.getFirstName().equals(dbAccount.getFirstName())
                    && account.getLastName().equals(dbAccount.getLastName())
                    && account.getEmail().equals(dbAccount.getEmail())
                    && account.getInsuranceAccountNumber().equals(dbAccount.getInsuranceAccountNumber());
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isEnabledAccount(Accounts account) {
        Accounts dbAccount = accountsRepository.findByInsuranceAccountNumber(account.getInsuranceAccountNumber());
        if (dbAccount == null) {
            return false;
        }
        return dbAccount.isEnabled();
    }

    public void update(Accounts account, Map<String, String> updateSet) throws IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        for (Entry<String, String> entry : updateSet.entrySet()) {
            Method method = account.getClass().getDeclaredMethod(entry.getKey());
            method.invoke(account, entry.getValue());
        }

        System.out.println(account);
    }

    public Accounts getAccountFromDatabase(String insuranceAccountNumber) {
        return accountsRepository.findByInsuranceAccountNumber(insuranceAccountNumber);
    }

    public void setOtpData(Accounts accounts, String otp, String temporaryPassword) {
        accounts.setOneTimePassword(otp);
        accounts.setOtpRequestedTime(System.currentTimeMillis());
        accounts.setTemporaryPassword(temporaryPassword);
        accountsRepository.save(accounts);
    }
}
