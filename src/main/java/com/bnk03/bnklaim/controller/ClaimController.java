package com.bnk03.bnklaim.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/case")
    public ResponseEntity<String> addCase(@RequestBody Claim claim) {
        return new ResponseEntity<>("AAA", httpHeaders, HttpStatus.OK);
    }
}
