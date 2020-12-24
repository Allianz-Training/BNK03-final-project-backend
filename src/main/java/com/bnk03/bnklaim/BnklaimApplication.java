package com.bnk03.bnklaim;

import java.util.Optional;

import com.bnk03.bnklaim.controller.UserAccountsController;
import com.bnk03.bnklaim.entities.CollectionId;
import com.bnk03.bnklaim.entities.UserAccounts;
import com.bnk03.bnklaim.repositories.CollectionIdRepository;
import com.bnk03.bnklaim.repositories.UserAccountsRepository;
import com.bnk03.bnklaim.service.CollectionIdService;
import com.bnk03.bnklaim.service.UserAccountsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BnklaimApplication implements CommandLineRunner {

	@Autowired
	private CollectionIdService collectionIdService;

	public static void main(String[] args) {
		SpringApplication.run(BnklaimApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		collectionIdService.increaseNextId("case");
	}
}
