package com.bnk03.bnklaim.entity;

import com.bnk03.bnklaim.utility.ObjectToJson;

import org.springframework.data.annotation.Id;

public class ThirdPartyDetail {
    @Id
    private String caseId;
    private String firstName;
    private String lastName;
    private String carRegistration;
    private String phone;
    private String dateOfAccident;
    private String timeOfAccident;
    private String carRegistrationImage;
    private String driverLicenseImage;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
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

    public String getCarRegistration() {
        return carRegistration;
    }

    public void setCarRegistration(String carRegistration) {
        this.carRegistration = carRegistration;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfAccident() {
        return dateOfAccident;
    }

    public void setDateOfAccident(String dateOfAccident) {
        this.dateOfAccident = dateOfAccident;
    }

    public String getTimeOfAccident() {
        return timeOfAccident;
    }

    public void setTimeOfAccident(String timeOfAccident) {
        this.timeOfAccident = timeOfAccident;
    }

    public String getCarRegistrationImage() {
        return carRegistrationImage;
    }

    public void setCarRegistrationImage(String carRegistrationImage) {
        this.carRegistrationImage = carRegistrationImage;
    }

    public String getDriverLicenseImage() {
        return driverLicenseImage;
    }

    public void setDriverLicenseImage(String driverLicenseImage) {
        this.driverLicenseImage = driverLicenseImage;
    }

    @Override
    public String toString() {
        return ObjectToJson.toJsonString(this);
    }
}
