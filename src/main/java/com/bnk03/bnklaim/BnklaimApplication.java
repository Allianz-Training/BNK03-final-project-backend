package com.bnk03.bnklaim;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BnklaimApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BnklaimApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n\n\n\n==========RUN==========\n\n\n\n");
	}
}
