package com.bnk03.bnklaim.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
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

    private static final long OTP_LIMIT_TIME = 5 * 60 * 1000;

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
        System.out.println(insuranceAccountNumber);
        return accountsRepository.findByInsuranceAccountNumber(insuranceAccountNumber);
    }

    public void setRequestedOtpData(Accounts account, String otp, String temporaryPassword) {
        account.setOneTimePassword(otp);
        account.setOtpRequestedTime(System.currentTimeMillis());
        account.setTemporaryPassword(bcrypString(temporaryPassword));
        accountsRepository.save(account);
    }

    public void setRegisterSuccess(Accounts account) {
        account.setPasswordHash(account.getTemporaryPassword());
        account.setEnabled(true);
        accountsRepository.save(account);
    }

    public void clearOtpAndTemp(Accounts account) {
        account.setOneTimePassword(null);
        account.setOtpRequestedTime(null);
        account.setTemporaryPassword(null);
        accountsRepository.save(account);
    }

    public boolean isInOtpRequestedTime(Accounts account) {
        try {
            return System.currentTimeMillis() - account.getOtpRequestedTime() <= OTP_LIMIT_TIME;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOtpValid(Accounts account, String otpInputString) {
        return otpInputString.equals(account.getOneTimePassword());
    }
}
