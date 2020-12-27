package com.bnk03.bnklaim.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@RequestMapping("/claim")
public class ClaimController {
    private HttpHeaders httpHeaders = new HttpHeaders();

    private ClaimController() {
        httpHeaders.set("Content-Type", "application/json");
    }

}
