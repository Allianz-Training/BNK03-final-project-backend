package com.bnk03.bnklaim.controller;

import com.bnk03.bnklaim.service.UserAccountsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class UserAccountsController {
    @Autowired
    private UserAccountsService userAccountsService;
}
