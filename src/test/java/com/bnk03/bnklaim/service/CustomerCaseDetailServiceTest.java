package com.bnk03.bnklaim.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.bnk03.bnklaim.entity.CustomerCaseDetail;
import com.bnk03.bnklaim.repositories.CustomerCaseDetailRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerCaseDetailServiceTest {
    @Mock
    CustomerCaseDetail customerCaseDetail = new CustomerCaseDetail();

    @Mock
    CustomerCaseDetailRepository customerCaseDetailRepository;

    @InjectMocks
    CustomerCaseDetailService customerCaseDetailService;

    @Test
    void testCustomerCaseDetail() {
        Integer expected_caseId = 1;
        customerCaseDetailService.saveCustomerCaseDetail(customerCaseDetail, 1);

        verify(customerCaseDetail, times(1)).setCaseId(expected_caseId.toString());
        verify(customerCaseDetailRepository, times(1)).save(customerCaseDetail);
    }
}
