package com.bnk03.bnklaim;

import com.bnk03.bnklaim.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BnklaimApplication {

	public static void main(String[] args) {
		SpringApplication.run(BnklaimApplication.class, args);
	}

}
