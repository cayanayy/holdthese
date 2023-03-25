package com.cayanay.holdthese.business.abstracts;

import com.cayanay.holdthese.business.requests.CreateAccessCodeRequest;
import com.cayanay.holdthese.entities.AccessCode;

public interface AccessCodeService {
    void createAccessCode(CreateAccessCodeRequest createAccessCodeRequest);

    AccessCode getAccessCodeByCode(String code);
}
