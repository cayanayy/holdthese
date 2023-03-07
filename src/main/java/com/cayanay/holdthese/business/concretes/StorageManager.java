package com.cayanay.holdthese.business.concretes;

import com.cayanay.holdthese.business.abstracts.StorageService;
import com.cayanay.holdthese.business.requests.DownloadFileRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Service
public class StorageManager implements StorageService {
    private final String FOLDER_DIRECTORY = "D:\\src\\intellij projects\\holdthese\\files\\";

    public void uploadFile(MultipartFile file, String accessCode) throws IOException {
        file.transferTo(new java.io.File(FOLDER_DIRECTORY + accessCode));
    }

    public byte[] downloadFile(DownloadFileRequest downloadFileRequest) throws IOException {
        String filePath = FOLDER_DIRECTORY + downloadFileRequest.getAccessCode();
        byte[] fileData = Base64.getEncoder().encode(Files.readAllBytes(new File(filePath).toPath()));
        return fileData;
    }

}
