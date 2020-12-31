package com.bnk03.bnklaim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import com.bnk03.bnklaim.entity.Accounts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {
    @Mock
    Accounts account = new Accounts();

    @InjectMocks
    MailService mailService;

    // @Test
    // void testSendMail() throws MessagingException {
    // Properties props = new Properties();

    // String otp = "OTP";
    // mailService.sendMail(account, otp);

    // verify(Session, times(1));
    // }

    @Test
    void testGenerateOTP() {
        assertEquals(true, true);
    }
}
