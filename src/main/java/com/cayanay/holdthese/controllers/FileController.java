package com.cayanay.holdthese.controllers;

import com.cayanay.holdthese.business.concretes.FileManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/file")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FileController {
    private final FileManager fileManager;

    @GetMapping("{fileCode}")
    public byte[] downloadFile(@Valid @PathVariable("fileCode") String fileCode) throws IOException {
        return fileManager.downloadFile(fileCode);
    }

    @DeleteMapping("{fileCode}")
    public void deleteFile(@Valid @PathVariable("fileCode") String fileCode) {
        fileManager.deleteFile(fileCode);
    }
}
