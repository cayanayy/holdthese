package com.cayanay.holdthese.business.rules;

import com.cayanay.holdthese.core.utilities.exceptions.BusinessException;
import com.cayanay.holdthese.dataaccess.AccessCodeRepository;
import com.cayanay.holdthese.entities.AccessCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class AccessCodeBusinessRules {
    private AccessCodeRepository accessCodeRepository;

    public void checkIfAccessCodeExists(String code) {
        if (accessCodeRepository.existsByCode(code)) {
            throw new BusinessException("Access code already exits!");
        }
    }

    public void checkIfAccessCodeDoesNotExists(String code) {
        if (!accessCodeRepository.existsByCode(code)) {
            throw new BusinessException("Access code does not exists!");
        }
    }


    public void checkIfExpired(String code) {
        AccessCode accessCode = accessCodeRepository.findAccessCodeByCode(code);
        String message = "This access code expired!";
        if (accessCode.getExpired()) {
            throw new BusinessException(message);
        } else if (accessCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            accessCodeRepository.updateExpire(code, true);
            throw new BusinessException(message);
        }
    }
}
