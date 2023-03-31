package com.cayanay.holdthese.business.abstracts;

import com.cayanay.holdthese.entities.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    void createFile(File file);

    void deleteFile(String fileCode);

    void uploadFile(MultipartFile file, String accessCode) throws IOException;

    byte[] downloadFile(String fileCode) throws IOException;
}
