package com.cayanay.holdthese.business.abstracts;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    void uploadFile(MultipartFile file, String accessCode) throws IOException;

    byte[] downloadFile(String fileCode) throws IOException;
}
