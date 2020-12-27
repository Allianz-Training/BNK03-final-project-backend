package com.bnk03.bnklaim.entity;

import com.bnk03.bnklaim.utility.ObjectToJson;

import org.springframework.data.annotation.Id;

public class Accounts {
    @Id
    private String userId;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String insuranceAccountNumber;
    private String passwordHash;
    private boolean isEnabled;
    private String oneTimePassword;
    private Long otpRequestedTime;
    private String temporaryPassword;

    public Accounts() {
        // constructor
    }

    public Accounts(String firstName, String lastName, String email, String insuranceAccountNumber,
            String temporaryPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.insuranceAccountNumber = insuranceAccountNumber;
        this.temporaryPassword = temporaryPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInsuranceAccountNumber() {
        return insuranceAccountNumber;
    }

    public void setInsuranceAccountNumber(String insuranceAccountNumber) {
        this.insuranceAccountNumber = insuranceAccountNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(String isEnabled) {
        this.isEnabled = isEnabled.equals("true");
    }

    public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    public Long getOtpRequestedTime() {
        return otpRequestedTime;
    }

    public void setOtpRequestedTime(Long otpRequestedTime) {
        this.otpRequestedTime = otpRequestedTime;
    }

    public String getTemporaryPassword() {
        return temporaryPassword;
    }

    public void setTemporaryPassword(String temporaryPassword) {
        this.temporaryPassword = temporaryPassword;
    }

    @Override
    public String toString() {
        return ObjectToJson.toJsonString(this);
    }
}
