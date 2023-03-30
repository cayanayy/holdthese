package com.cayanay.holdthese.controllers;

import com.cayanay.holdthese.business.concretes.StorageManager;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/file")
@AllArgsConstructor
public class StorageController {
    StorageManager storageManager;

    @GetMapping("{fileCode}")
    public byte[] downloadFile(@Valid @PathVariable String fileCode) throws IOException {
        return storageManager.downloadFile(fileCode);
    }
}
