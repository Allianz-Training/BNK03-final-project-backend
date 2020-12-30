package com.bnk03.bnklaim.entity;

import org.springframework.data.annotation.Id;

public class Claim {
    @Id
    private String id;
    private CustomerCaseDetail customerCaseDetail;
    private ThirdPartyDetail thirdPartyDetail;
    
}
