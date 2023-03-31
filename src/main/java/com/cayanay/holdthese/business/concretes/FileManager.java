package com.cayanay.holdthese.business.concretes;

import com.cayanay.holdthese.business.abstracts.FileService;
import com.cayanay.holdthese.business.rules.FileBusinessRules;
import com.cayanay.holdthese.dataaccess.FileRepository;
import com.cayanay.holdthese.entities.File;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class FileManager implements FileService {
    private final FileRepository fileRepository;
    private final FileBusinessRules fileBusinessRules;

    @Value("${FOLDER_DIRECTORY}")
    private String FOLDER_DIRECTORY;

    @Override
    public void createFile(File file) {
        fileRepository.save(file);
    }

    @Override
    public void deleteFile(String fileCode) {
        fileBusinessRules.checkFileDoesNotExits(fileCode);
        fileRepository.deleteByFileAccessCode(fileCode);
    }

    @Override
    public void uploadFile(MultipartFile file, String accessCode) throws IOException {
        System.out.println(FOLDER_DIRECTORY);
        file.transferTo(new java.io.File(FOLDER_DIRECTORY + accessCode));
    }

    @Override
    public byte[] downloadFile(String fileCode) throws IOException {
        String filePath = FOLDER_DIRECTORY.concat(fileCode);
        return Base64.getEncoder().encode(Files.readAllBytes(new java.io.File(filePath).toPath()));
    }

}
