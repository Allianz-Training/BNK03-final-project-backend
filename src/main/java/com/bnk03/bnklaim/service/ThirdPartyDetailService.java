package com.bnk03.bnklaim.service;

import com.bnk03.bnklaim.entity.ThirdPartyDetail;
import com.bnk03.bnklaim.repositories.ThirdPartyDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ThirdPartyDetailService {
    @Autowired
    private ThirdPartyDetailRepository thirdPartyDetailRepository;

    public ThirdPartyDetailService() {
        // constructor
    }

    public void saveThirdPartyDetail(ThirdPartyDetail thirdPartyDetail, Integer caseId) {
        thirdPartyDetail.setCaseId(caseId.toString());
        thirdPartyDetailRepository.save(thirdPartyDetail);
    }
}
