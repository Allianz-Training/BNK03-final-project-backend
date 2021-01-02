package com.bnk03.bnklaim.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@ControllerAdvice
@RestController
public class HealthCheckController {
    @GetMapping("/")
    public String test() {
        return "API works!";
    }
}
