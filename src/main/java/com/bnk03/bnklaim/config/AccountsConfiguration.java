package com.bnk03.bnklaim.config;

import com.bnk03.bnklaim.service.CustomerCaseDetailService;
import com.bnk03.bnklaim.service.MailService;
import com.bnk03.bnklaim.service.ThirdPartyDetailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AccountsConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MailService mailService() {
        return new MailService();
    }

    @Bean
    public ThirdPartyDetailService thirdpartyDetailService() {
        return new ThirdPartyDetailService();
    }

    @Bean
    public CustomerCaseDetailService customercaseDetailService() {
        return new CustomerCaseDetailService();
    }
}
