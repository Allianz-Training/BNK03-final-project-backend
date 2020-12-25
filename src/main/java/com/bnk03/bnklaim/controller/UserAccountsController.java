package com.bnk03.bnklaim.controller;

import com.bnk03.bnklaim.entities.UserAccounts;
import com.bnk03.bnklaim.service.UserAccountsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@RequestMapping("/accounts")
public class UserAccountsController {
    @Autowired
    private UserAccountsService userAccountsService;

    private HttpHeaders responseHeaders = new HttpHeaders();

    private UserAccountsController() {
        responseHeaders.set("Content-Type", "application/json");
    }

    @PutMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserAccounts userInput) {
        if (userAccountsService.isValidUserAccountData(userInput)) {
            if (!userAccountsService.isRegisteredAccount(userInput)) {
                return new ResponseEntity<>("{\"status\":\"201\",\"message\":\"Success\"}", responseHeaders,
                        HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("{\"status\":\"409\",\"message\":\"This account already registered\"}",
                        responseHeaders, HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>("{\"status\":\"401\",\"message\":\"Invalid information\"}", responseHeaders,
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
