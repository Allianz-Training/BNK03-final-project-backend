package com.bnk03.bnklaim.controller;

import javax.mail.MessagingException;

// import java.util.Map;

import com.bnk03.bnklaim.entity.Accounts;
import com.bnk03.bnklaim.service.AccountService;
import com.bnk03.bnklaim.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
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

    private AccountController() {
        httpHeaders.set("Content-Type", "application/json");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return new ResponseEntity<>("login", httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/register")
    public ResponseEntity<String> register(@RequestBody Accounts account) throws MessagingException {
        if (accountService.isUserExistedInDB(account)) {
            if (!accountService.isEnabledAccount(account)) {
                Accounts dbAccount = accountService.getAccountFromDatabase(account.getInsuranceAccountNumber());
                String otp = mailService.generateOTP(4);
                accountService.setOtpData(dbAccount, otp, account.getTemporaryPassword());
                mailService.sendMail(dbAccount, otp);

                return new ResponseEntity<>("{\"status\":\"201\",\"message\":\"Success\"}", httpHeaders,
                        HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("{\"status\":\"409\",\"message\":\"This account already registered\"}",
                        httpHeaders, HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("{\"status\":\"401\",\"message\":\"Invalid information\"}", httpHeaders,
                HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/register/otp")
    public ResponseEntity<String> otp() {
        return new ResponseEntity<>("otp", httpHeaders, HttpStatus.CREATED);
    }
}
