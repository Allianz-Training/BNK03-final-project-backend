package com.bnk03.bnklaim.controller;

import com.bnk03.bnklaim.entity.Claim;
import com.bnk03.bnklaim.entity.CustomerCaseDetail;
import com.bnk03.bnklaim.entity.ThirdPartyDetail;
import com.bnk03.bnklaim.exception.DataNotFoundException;
import com.bnk03.bnklaim.service.CollectionIdService;
import com.bnk03.bnklaim.service.CustomerCaseDetailService;
import com.bnk03.bnklaim.service.ThirdPartyDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@ControllerAdvice
@RestController
@RequestMapping("/claim")
public class ClaimController {
    @Autowired
    private CollectionIdService collectionIdService;
    @Autowired
    private CustomerCaseDetailService customerCaseDetailService;
    @Autowired
    private ThirdPartyDetailService thirdPartyDetailService;

    private HttpHeaders httpHeaders = new HttpHeaders();
    private static final String STATUSSTRING = "{\"status\":";

    private ClaimController() {
        httpHeaders.set("Content-Type", "application/json");
    }

    @PostMapping("/case")
    public ResponseEntity<String> addCase(@RequestBody Claim claim) {
        CustomerCaseDetail customerCaseDetail = claim.getCustomerCaseDetail();
        ThirdPartyDetail thirdPartyDetail = claim.getThirdPartyDetail();
        Integer caseId;
        try {
            caseId = collectionIdService.increaseCollectionId("case", 1);
            customerCaseDetailService.saveCustomerCaseDetail(customerCaseDetail, caseId);
            thirdPartyDetailService.saveThirdPartyDetail(thirdPartyDetail, caseId);
            return new ResponseEntity<>(STATUSSTRING + HttpStatus.CREATED.value() + ",\"message\":\"Success\"}",
                    httpHeaders, HttpStatus.CREATED);
        } catch (DataNotFoundException e) {
            return new ResponseEntity<>(
                    STATUSSTRING + HttpStatus.NOT_FOUND.value() + ",\"message\":\"Collection not found\"}", httpHeaders,
                    HttpStatus.NOT_FOUND);
        }

    }
}
