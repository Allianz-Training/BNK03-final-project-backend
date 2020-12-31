package com.bnk03.bnklaim.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.bnk03.bnklaim.entity.ThirdPartyDetail;
import com.bnk03.bnklaim.repositories.ThirdPartyDetailRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ThirdPartyDetailServiceTest {

    @Mock
    ThirdPartyDetail thirdPartyDetail = new ThirdPartyDetail();

    @Mock
    ThirdPartyDetailRepository thirdPartyDetailRepository;

    @InjectMocks
    ThirdPartyDetailService thirdPartyDetailService;

    @Test
    void testThirdPartyDetail() {
        Integer expected_caseId = 1;
        thirdPartyDetailService.saveThirdPartyDetail(thirdPartyDetail, 1);

        verify(thirdPartyDetail, times(1)).setCaseId(expected_caseId.toString());
        verify(thirdPartyDetailRepository, times(1)).save(thirdPartyDetail);
    }
}
