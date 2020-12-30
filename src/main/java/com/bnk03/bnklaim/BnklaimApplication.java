package com.bnk03.bnklaim;

//import com.bnk03.bnklaim.entity.UserInformation;
import com.bnk03.bnklaim.repositories.UserInformationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BnklaimApplication {
//	@Autowired
//	private UserInformationRepository userInformationRepository;
//
//	private UserInformation uInfo = new UserInformation();

	public static void main(String[] args) {
		SpringApplication.run(BnklaimApplication.class, args);

	}

	// @Bean
	// public void test() {
	// System.out.println(userInformationRepository.findAll());
	// uInfo.setInsuranceAccountNumber("123456789012");
	// userInformationRepository.save(uInfo);
	// }
}
