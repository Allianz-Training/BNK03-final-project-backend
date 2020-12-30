package com.bnk03.bnklaim.controller;

import com.bnk03.bnklaim.entity.UserInformation;
import com.bnk03.bnklaim.service.UserInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@ControllerAdvice
@RestController
@RequestMapping("/user")
public class UserInformationController {
    @Autowired
    private UserInformationService userInformationService;

    private HttpHeaders httpHeaders = new HttpHeaders();

    private static final String STATUSSTRING = "{\"status\":";

    private UserInformationController() {
        httpHeaders.set("Content-Type", "application/json");
    }

    @GetMapping("/information/{id}")
    public ResponseEntity<String> getInformation(@PathVariable("id") String insuranceAccountNumber) {
        UserInformation uInfo;
        try {
            uInfo = userInformationService.getUserInfo(insuranceAccountNumber);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    STATUSSTRING + HttpStatus.NOT_FOUND.value() + ",\"message\":\"Insurance Account not found\"}",
                    httpHeaders, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(STATUSSTRING + HttpStatus.OK.value() + ",\"message\":" + uInfo + "}", httpHeaders,
                HttpStatus.OK);
    }
}
