package com.bnk03.bnklaim.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bnk03.bnklaim.entity.Accounts;
import com.bnk03.bnklaim.repositories.AccountsRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountsRepository accountsRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    AccountService accountService;

    @Test
    void testBcryptString() {
        String expected = "DUMMY_BCRYPT";
        accountService.bcrypString("DUMMY_BCRYPT");

        verify(bCryptPasswordEncoder, times(1)).encode(expected);
    }

    @Test
    void TestIsMatchShouldReturnTrue() {
        String expected_string = "DUMMY_STRING";
        String expected_encodedString = "DUMMY_ENCODEDSTRING";
        accountService.isMatch("DUMMY_STRING", "DUMMY_ENCODEDSTRING");

        verify(bCryptPasswordEncoder, times(1)).matches(expected_string, expected_encodedString);
    }

    @Test
    void testIsUserExistedInDBIfUserExistShouldReturnTrue() {
        Accounts expected = new Accounts();
        expected.setFirstName("FIRSTNAME");
        expected.setLastName("LASTNAME");
        expected.setEmail("EMAIL");
        expected.setInsuranceAccountNumber("INSURANCEACCOUNTNUMBER");

        Accounts actual = new Accounts();
        actual.setFirstName("FIRSTNAME");
        actual.setLastName("LASTNAME");
        actual.setEmail("EMAIL");
        actual.setInsuranceAccountNumber("INSURANCEACCOUNTNUMBER");

        when(accountsRepository.findByInsuranceAccountNumber(expected.getInsuranceAccountNumber())).thenReturn(actual);

        assertTrue(accountService.isUserExistedInDB(expected));
    }

    @Test
    void testIsUserExistedInDBIfUserNotExistShouldReturnFalse() {
        Accounts fakeAccount = new Accounts();
        fakeAccount.setInsuranceAccountNumber("DUMMY");
        when(accountsRepository.findByInsuranceAccountNumber(anyString())).thenReturn(null);
        assertFalse(accountService.isUserExistedInDB(fakeAccount));
    }

    @Test
    void testIsUserExistedInDBIfDataNotMatchShouldReturnFalse() {
        Accounts expectedInput = new Accounts();
        expectedInput.setFirstName("FIRSTNAME");
        expectedInput.setLastName("LASTNAME");
        expectedInput.setEmail("EMAIL");
        expectedInput.setInsuranceAccountNumber("INSURANCEACCOUNTNUMBER");

        Accounts actualDB = new Accounts();
        actualDB.setFirstName("FIRSTNAME");
        actualDB.setLastName("LASTNAME");
        actualDB.setEmail("EMAIL");
        actualDB.setInsuranceAccountNumber("INSURANCEACCOUNTNUMBER");

        when(accountsRepository.findByInsuranceAccountNumber(expectedInput.getInsuranceAccountNumber()))
                .thenReturn(actualDB);

        expectedInput.setFirstName("WRONG_FIRSTNAME");
        assertFalse(accountService.isUserExistedInDB(expectedInput));
        expectedInput.setFirstName("FIRSTNAME");

        expectedInput.setLastName("WRONG_LASTNAME");
        assertFalse(accountService.isUserExistedInDB(expectedInput));
        expectedInput.setLastName("LASTNAME");

        expectedInput.setEmail("WRONG_EMAIL");
        assertFalse(accountService.isUserExistedInDB(expectedInput));
    }

    @Test
    void testIsUserExistedInDBIfMissingDataShouldReturnFalse() {
        Accounts expectedInput = new Accounts();
        expectedInput.setFirstName("FIRSTNAME");
        expectedInput.setLastName("LASTNAME");
        expectedInput.setInsuranceAccountNumber("INSURANCEACCOUNTNUMBER");

        Accounts actualDB = new Accounts();
        actualDB.setFirstName("FIRSTNAME");
        actualDB.setLastName("LASTNAME");
        actualDB.setEmail("EMAIL");
        actualDB.setInsuranceAccountNumber("INSURANCEACCOUNTNUMBER");

        when(accountsRepository.findByInsuranceAccountNumber(expectedInput.getInsuranceAccountNumber()))
                .thenReturn(actualDB);

        assertFalse(accountService.isUserExistedInDB(expectedInput));
    }

    @Test
    void testIsEnabledAccountShouldReturnTrueIfFieldEnabledIsTrue() {
        Accounts expectedInput = new Accounts();
        expectedInput.setInsuranceAccountNumber("INSURANCEACCOUNTNUMBER");

        Accounts actualDB = new Accounts();
        actualDB.setEnabled(true);
        actualDB.setInsuranceAccountNumber("INSURANCEACCOUNTNUMBER");

        when(accountsRepository.findByInsuranceAccountNumber("INSURANCEACCOUNTNUMBER")).thenReturn(actualDB);
        assertTrue(accountService.isEnabledAccount(expectedInput));
    }

    @Test
    void testIsEnabledAccountShouldReturnFalseIfAccountNotFound() {
        Accounts expectedInput = new Accounts();
        expectedInput.setInsuranceAccountNumber("INSURANCEACCOUNTNUMBER");

        when(accountsRepository.findByInsuranceAccountNumber("INSURANCEACCOUNTNUMBER")).thenReturn(null);
        assertFalse(accountService.isEnabledAccount(expectedInput));
    }

    @Test
    void testGetAccountFromDataBase() {
        accountService.getAccountFromDatabase("insuranceAccountNumber");
        verify(accountsRepository, times(1)).findByInsuranceAccountNumber("insuranceAccountNumber");
    }

    @Test
    void testGetAccountFromDataBaseByEmail() {
        accountService.getAccountFromDatabaseByEmail("email");
        verify(accountsRepository, times(1)).findByEmail("email");
    }

    @Test
    void testSetRequestedOtpData() {
        Accounts account = new Accounts();
        String expected_otp = "OTP";
        String expected_temporaryPassword = "TEMP";

        accountService.setRequestedOtpData(account, expected_otp, expected_temporaryPassword);

        verify(accountsRepository, times(1)).save(account);
    }

    @Test
    void testClearOtpAndTemp() {
        Accounts account = new Accounts();

        accountService.clearOtpAndTemp(account);

        verify(accountsRepository, times(1)).save(account);
    }

    @Test
    void testDisableAccount() {
        Accounts account = new Accounts();

        accountService.disableAccount(account);

        verify(accountsRepository, times(1)).save(account);
    }

    @Test
    void testSetRegisterSuccess() {
        Accounts account = new Accounts();

        accountService.setRegisterSuccess(account);

        verify(accountsRepository, times(1)).save(account);
    }

    @Test
    void testIsInOtpRequestedTimeReturnTrue() {
        Accounts account = new Accounts();
        account.setOtpRequestedTime(System.currentTimeMillis());

        assertTrue(accountService.isInOtpRequestedTime(account));
    }

    @Test
    void testIsInOtpRequestedTimerReturnFalseIfTimeDifferenceThanFiveMins() {
        Accounts account = new Accounts();
        account.setOtpRequestedTime(System.currentTimeMillis() - ((long) 6 * 60 * 1000));

        assertFalse(accountService.isInOtpRequestedTime(account));
    }

    @Test
    void testIsInOtpRequestedTimeReturnFalseIfAccountNoOTPRequestedTime() {
        Accounts account = new Accounts();
        assertFalse(accountService.isInOtpRequestedTime(account));
    }

    @Test
    void testIsOtpValid() {
        Accounts account = new Accounts();
        account.setOneTimePassword("OTP_INPUT_STRING");
        String otpInputString = "OTP_INPUT_STRING";

        assertTrue(accountService.isOtpValid(account, otpInputString));
    }
}
