package com.bnk03.bnklaim.entity;

import com.bnk03.bnklaim.utility.ObjectToJson;

import org.springframework.data.annotation.Id;

public class Claim {
    @Id
    private String id;
    private CustomerCaseDetail customerCaseDetail;
    private ThirdPartyDetail thirdPartyDetail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerCaseDetail getCustomerCaseDetail() {
        return customerCaseDetail;
    }

    public void setCustomerCaseDetail(CustomerCaseDetail customerCaseDetail) {
        this.customerCaseDetail = customerCaseDetail;
    }

    public ThirdPartyDetail getThirdPartyDetail() {
        return thirdPartyDetail;
    }

    public void setThirdPartyDetail(ThirdPartyDetail thirdPartyDetail) {
        this.thirdPartyDetail = thirdPartyDetail;
    }

    @Override
    public String toString() {
        return ObjectToJson.toJsonString(this);
    }
}
