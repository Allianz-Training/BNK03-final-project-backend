package com.bnk03.bnklaim.service;

import com.bnk03.bnklaim.entity.CustomerCaseDetail;
import com.bnk03.bnklaim.repositories.CustomerCaseDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCaseDetailService {
    @Autowired
    private CustomerCaseDetailRepository customerCaseDetailRepository;

    public void savecustomerCaseDetail(CustomerCaseDetail customerCaseDetail, Integer caseId) {
        customerCaseDetail.setCaseId(caseId.toString());
        customerCaseDetailRepository.save(customerCaseDetail);
    }
}
