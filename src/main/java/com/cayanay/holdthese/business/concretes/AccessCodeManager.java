package com.cayanay.holdthese.business.concretes;

import com.cayanay.holdthese.business.abstracts.AccessCodeService;
import com.cayanay.holdthese.business.requests.CreateAccessCodeRequest;
import com.cayanay.holdthese.business.rules.AccessCodeBusinessRules;
import com.cayanay.holdthese.core.utilities.mappers.ModelMapperManager;
import com.cayanay.holdthese.dataaccess.AccessCodeRepository;
import com.cayanay.holdthese.entities.AccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccessCodeManager implements AccessCodeService {
    private final AccessCodeRepository accessCodeRepository;
    private final ModelMapperManager modelMapperManager;
    private final AccessCodeBusinessRules accessCodeBusinessRules;

    @Override
    public void createAccessCode(CreateAccessCodeRequest createAccessCodeRequest) {
        accessCodeBusinessRules.checkIfAccessCodeExists(createAccessCodeRequest.getCode());
        AccessCode accessCode = modelMapperManager.forRequest().map(createAccessCodeRequest, AccessCode.class);
        accessCode.setExpiresAt(LocalDateTime.now().plusMinutes(createAccessCodeRequest.getDuration()));
        accessCodeRepository.save(accessCode);
    }

    @Override
    public AccessCode getAccessCodeByCode(String code) {
        accessCodeBusinessRules.checkIfAccessCodeDoesNotExists(code);
        accessCodeBusinessRules.checkIfExpired(code);

        return accessCodeRepository.findAccessCodeByCode(code);
    }
}
