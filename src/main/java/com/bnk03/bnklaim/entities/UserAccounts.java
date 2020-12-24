package com.bnk03.bnklaim.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.data.annotation.Id;

public class UserAccounts {
    @Id
    private String userId;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String insuranceAccountNumber;
    private String passwordHash;

    private ObjectMapper mapper = new ObjectMapper();

    public UserAccounts() {
        // constructor
    }

    public UserAccounts(String firstName, String lastName, String email, String phone, String insuranceAccountNumber,
            String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.insuranceAccountNumber = insuranceAccountNumber;
        this.passwordHash = passwordHash;
    }

    public UserAccounts(String userId, String firstName, String lastName, String email, String phone,
            String insuranceAccountNumber, String passwordHash) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.insuranceAccountNumber = insuranceAccountNumber;
        this.passwordHash = passwordHash;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getInsuranceAccountNumber() {
        return insuranceAccountNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setInsuranceAccountNumber(String insuranceAccountNumber) {
        this.insuranceAccountNumber = insuranceAccountNumber;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
