package com.cayanay.holdthese.business.concretes;

import com.cayanay.holdthese.business.abstracts.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class StorageManager implements StorageService {
    @Value("${FOLDER_DIRECTORY}")
    private String FOLDER_DIRECTORY;

    public void uploadFile(MultipartFile file, String accessCode) throws IOException {
        System.out.println(FOLDER_DIRECTORY);
        file.transferTo(new java.io.File(FOLDER_DIRECTORY + accessCode));
    }

    public byte[] downloadFile(String fileCode) throws IOException {
        String filePath = FOLDER_DIRECTORY.concat(fileCode);
        return Base64.getEncoder().encode(Files.readAllBytes(new File(filePath).toPath()));
    }
}
