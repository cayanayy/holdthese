package com.cayanay.holdthese.business.concretes;

import com.cayanay.holdthese.business.abstracts.AccessCodeService;
import com.cayanay.holdthese.business.requests.CreateAccessCodeRequest;
import com.cayanay.holdthese.business.requests.GetAccessCodeRequest;
import com.cayanay.holdthese.business.rules.AccessCodeBusinessRules;
import com.cayanay.holdthese.core.utilities.mappers.ModelMapperManager;
import com.cayanay.holdthese.dataaccess.AccessCodeRepository;
import com.cayanay.holdthese.entities.AccessCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AccessCodeManager implements AccessCodeService {
    private final AccessCodeRepository accessCodeRepository;
    private final ModelMapperManager modelMapperManager;
    private final AccessCodeBusinessRules accessCodeBusinessRules;

    @Override
    public void createAccessCode(CreateAccessCodeRequest createAccessCodeRequest) {
        accessCodeBusinessRules.checkIfAccessCodeExists(createAccessCodeRequest.getCode());
        AccessCode accessCode = this.modelMapperManager.forRequest().map(createAccessCodeRequest, AccessCode.class);
        accessCode.setCreatedAt(LocalDateTime.now());
        accessCode.setExpiresAt(LocalDateTime.now().plusMinutes(createAccessCodeRequest.getDuration()));
        accessCodeRepository.save(accessCode);
    }

    @Override
    public AccessCode getAccessCodeByCode(GetAccessCodeRequest getAccessCodeRequest) {
        String code = getAccessCodeRequest.getCode();
        accessCodeBusinessRules.checkIfAccessCodeDoesNotExists(code);
        accessCodeBusinessRules.checkIfExpired(code);

        return accessCodeRepository.findAccessCodeByCode(code);
    }
}
