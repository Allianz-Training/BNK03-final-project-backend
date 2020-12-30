package com.bnk03.bnklaim.entity;

import com.bnk03.bnklaim.utility.ObjectToJson;

import org.springframework.data.annotation.Id;

public class CustomerCaseDetail {
    @Id
    private String caseId;
    private String ownerImage1;
    private String ownerImage2;
    private String description;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getOwnerImage1() {
        return ownerImage1;
    }

    public void setOwnerImage1(String ownerImage1) {
        this.ownerImage1 = ownerImage1;
    }

    public String getOwnerImage2() {
        return ownerImage2;
    }

    public void setOwnerImage2(String ownerImage2) {
        this.ownerImage2 = ownerImage2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ObjectToJson.toJsonString(this);
    }
}
