package com.bnk03.bnklaim.service;

import java.util.Optional;

import com.bnk03.bnklaim.entity.UserInformation;
import com.bnk03.bnklaim.exception.DataNotFoundException;
import com.bnk03.bnklaim.repositories.UserInformationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInformationService {
    @Autowired
    private UserInformationRepository userInformationRepository;

    public UserInformationService() {
        // constructor
    }

    public UserInformation getUserInfo(String insuranceAccountNumber) throws DataNotFoundException {
        Optional<UserInformation> uInfo = userInformationRepository.findById(insuranceAccountNumber);

        if (uInfo.isPresent()) {
            return uInfo.get();
        } else {
            throw new DataNotFoundException("This insurance account not found!");
        }
    }
}
