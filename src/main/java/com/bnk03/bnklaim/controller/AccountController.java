package com.bnk03.bnklaim.controller;

import javax.mail.MessagingException;

import com.bnk03.bnklaim.entity.Accounts;
import com.bnk03.bnklaim.service.AccountService;
import com.bnk03.bnklaim.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MailService mailService;

    private HttpHeaders httpHeaders = new HttpHeaders();

    private static final String STATUSSTRING = "{\"status\":";

    private AccountController() {
        httpHeaders.set("Content-Type", "application/json");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Accounts account) {
        Accounts dbAccount = accountService.getAccountFromDatabaseByEmail(account.getEmail());
        if (dbAccount != null) {
            if (accountService.isEnabledAccount(dbAccount)) {
                if (accountService.isMatch(account.getTemporaryPassword(), dbAccount.getPasswordHash())) {
                    return new ResponseEntity<>(
                            STATUSSTRING + HttpStatus.OK.value() + ",\"message\":\"Login Success\"}", httpHeaders,
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(
                            STATUSSTRING + HttpStatus.UNAUTHORIZED.value()
                                    + ",\"message\":\"Username or password Not match\"}",
                            httpHeaders, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(
                        STATUSSTRING + HttpStatus.CONFLICT.value()
                                + ",\"message\":\"This account didn't register yet\"}",
                        httpHeaders, HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(
                STATUSSTRING + HttpStatus.UNAUTHORIZED.value() + ",\"message\":\"Username or password Not match\"}",
                httpHeaders, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/register")
    public ResponseEntity<String> register(@RequestBody Accounts account) throws MessagingException {
        if (accountService.isUserExistedInDB(account)) {
            if (!accountService.isEnabledAccount(account)) {
                Accounts dbAccount = accountService.getAccountFromDatabase(account.getInsuranceAccountNumber());
                String otp = mailService.generateOTP(4);
                accountService.setRequestedOtpData(dbAccount, otp, account.getTemporaryPassword());
                mailService.sendMail(dbAccount, otp);

                return new ResponseEntity<>(STATUSSTRING + HttpStatus.OK.value() + ",\"message\":\"Success\"}",
                        httpHeaders, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        STATUSSTRING + HttpStatus.CONFLICT.value()
                                + ",\"message\":\"This account already registered\"}",
                        httpHeaders, HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(
                STATUSSTRING + HttpStatus.UNAUTHORIZED.value() + ",\"message\":\"Invalid information\"}", httpHeaders,
                HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/register/otp")
    public ResponseEntity<String> otp(@RequestBody Accounts accountInput) {
        Accounts account = accountService.getAccountFromDatabase(accountInput.getInsuranceAccountNumber());
        System.out.println(account);
        if (accountService.isInOtpRequestedTime(account)) {
            if (accountService.isOtpValid(account, accountInput.getOneTimePassword())) {
                accountService.setRegisterSuccess(account);
                accountService.clearOtpAndTemp(account);

                return new ResponseEntity<>(
                        STATUSSTRING + HttpStatus.CREATED.value() + ",\"message\":\"Register success\"}", httpHeaders,
                        HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        STATUSSTRING + HttpStatus.UNAUTHORIZED.value() + ",\"message\":\"Invalid OTP\"}", httpHeaders,
                        HttpStatus.UNAUTHORIZED);
            }
        }
        accountService.clearOtpAndTemp(account);

        return new ResponseEntity<>(STATUSSTRING + HttpStatus.REQUEST_TIMEOUT.value() + ",\"message\":\"OTP timeout\"}",
                httpHeaders, HttpStatus.REQUEST_TIMEOUT);
    }
}
