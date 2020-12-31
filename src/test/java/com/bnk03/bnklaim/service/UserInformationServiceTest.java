package com.bnk03.bnklaim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.bnk03.bnklaim.entity.UserInformation;
import com.bnk03.bnklaim.exception.DataNotFoundException;
import com.bnk03.bnklaim.repositories.UserInformationRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserInformationServiceTest {

    @Mock
    UserInformation userinformation = new UserInformation();

    @Mock
    UserInformationRepository userInformationRepository;

    @InjectMocks
    UserInformationService userInformationService;

    @Test
    void testGetUserInfoPresent() throws DataNotFoundException {
        when(userInformationRepository.findById(anyString())).thenReturn(Optional.of(userinformation));

        assertEquals(userinformation, userInformationService.getUserInfo("DUMMYINSURANCEACCOUNTNUMBER"));
        verify(userInformationRepository, times(1)).findById("DUMMYINSURANCEACCOUNTNUMBER");
    }

    @Test
    void testGetUserInfoNotPresent() {
        when(userInformationRepository.findById(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(DataNotFoundException.class,
                () -> userInformationService.getUserInfo("DUMMYINSURANCEACCOUNTNUMBER"));

        String expected_message = "This insurance account not found!";
        String actual_message = exception.getMessage();
        assertEquals(expected_message, actual_message);
    }
}
