package com.bnk03.bnklaim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testGenerateOTP() {
        int n = 4;
        String actual = mailService.generateOTP(n);

        assertTrue(actual.matches("^[a-zA-Z0-9]*$"));
        assertEquals(4, actual.length());
    }
}
