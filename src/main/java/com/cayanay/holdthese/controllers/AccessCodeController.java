package com.cayanay.holdthese.controllers;

import com.cayanay.holdthese.business.concretes.AccessCodeManager;
import com.cayanay.holdthese.business.concretes.FileManager;
import com.cayanay.holdthese.business.concretes.ItemManager;
import com.cayanay.holdthese.business.concretes.StorageManager;
import com.cayanay.holdthese.business.requests.CreateAccessCodeRequest;
import com.cayanay.holdthese.business.requests.CreateItemRequest;
import com.cayanay.holdthese.business.responses.AccessCodeResponse;
import com.cayanay.holdthese.business.responses.ItemResponse;
import com.cayanay.holdthese.core.utilities.Utils;
import com.cayanay.holdthese.core.utilities.mappers.ModelMapperManager;
import com.cayanay.holdthese.entities.AccessCode;
import com.cayanay.holdthese.entities.File;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api")
@AllArgsConstructor
@CrossOrigin("*")
public class AccessCodeController {
    private final AccessCodeManager accessCodeManager;
    private final ModelMapperManager modelMapperManager;
    private final ItemManager itemManager;
    private final StorageManager storageManager;
    private final FileManager fileManager;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createAccessCode(@Valid @RequestBody CreateAccessCodeRequest createAccessCodeRequest) {
        accessCodeManager.createAccessCode(createAccessCodeRequest);
    }

    @GetMapping("/{code}")
    public AccessCodeResponse getAccessCode(@Valid @PathVariable String code) {
        AccessCodeResponse accessCodeResponse = modelMapperManager.forResponse().map(accessCodeManager.getAccessCodeByCode(code), AccessCodeResponse.class);
        accessCodeResponse.setItems(itemManager.getItemsByCode(code).stream().map(item ->
                modelMapperManager.forItemResponse().map(item, ItemResponse.class)
        ).toList());

        return accessCodeResponse;
    }

    @PostMapping("/{code}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createItem(@Valid @ModelAttribute CreateItemRequest createItemRequest, @PathVariable String code) throws IOException {
        AccessCode accessCode = accessCodeManager.getAccessCodeByCode(code); // test for expiration
        List<File> files = new ArrayList<>();
        if (createItemRequest.getFiles() != null) {
            for (MultipartFile multipartFile :
                    createItemRequest.getFiles()) {
                String fileAccessCode = Utils.md5Hash(multipartFile.getOriginalFilename() + multipartFile.getContentType() + LocalDateTime.now());
                File file = File.builder().name(multipartFile.getOriginalFilename()).createdAt(LocalDateTime.now()).type(multipartFile.getContentType()).fileAccessCode(fileAccessCode).build();
                storageManager.uploadFile(multipartFile, fileAccessCode);
                fileManager.createFile(file);
                files.add(file);
            }
        }

        itemManager.createItem(createItemRequest, files, accessCode);
    }

}
