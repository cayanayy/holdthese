package com.cayanay.holdthese.controllers;

import com.cayanay.holdthese.business.concretes.StorageManager;
import com.cayanay.holdthese.business.requests.DownloadFileRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("api/file")
@AllArgsConstructor
public class StorageController {
    StorageManager storageManager;

    @GetMapping()
    public byte[] downloadFile(@Valid DownloadFileRequest downloadFileRequest) throws IOException {
        return storageManager.downloadFile(downloadFileRequest);
    }
}
