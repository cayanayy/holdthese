package com.cayanay.holdthese.business.rules;

import com.cayanay.holdthese.core.utilities.exceptions.BusinessException;
import com.cayanay.holdthese.dataaccess.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FileBusinessRules {
    private final FileRepository fileRepository;

    public void checkFileDoesNotExits(String fileCode) {
        if (!fileRepository.existsByFileAccessCode(fileCode)) {
            throw new BusinessException("File does not exits!");
        }
    }
}
