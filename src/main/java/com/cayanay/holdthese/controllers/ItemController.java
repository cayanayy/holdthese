package com.cayanay.holdthese.controllers;

import com.cayanay.holdthese.business.concretes.AccessCodeManager;
import com.cayanay.holdthese.business.concretes.FileManager;
import com.cayanay.holdthese.business.concretes.ItemManager;
import com.cayanay.holdthese.business.concretes.StorageManager;
import com.cayanay.holdthese.business.requests.CreateItemRequest;
import com.cayanay.holdthese.business.requests.GetAccessCodeRequest;
import com.cayanay.holdthese.business.responses.ItemResponse;
import com.cayanay.holdthese.core.utilities.mappers.ModelMapperManager;
import com.cayanay.holdthese.entities.AccessCode;
import com.cayanay.holdthese.entities.File;
import com.cayanay.holdthese.utils.Utils;
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
@RequestMapping("api/item")
@AllArgsConstructor
@CrossOrigin("*")
public class ItemController {
    private final ItemManager itemManager;
    private final AccessCodeManager accessCodeManager;
    private final FileManager fileManager;
    private final StorageManager storageManager;
    private final ModelMapperManager modelMapperManager;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createItem(@Valid @ModelAttribute CreateItemRequest createItemRequest) throws IOException {
        AccessCode accessCode = accessCodeManager.getAccessCodeByCode(new GetAccessCodeRequest(createItemRequest.getCode())); // test for expiration
        List<File> files = new ArrayList<>();
        if (createItemRequest.getFiles() != null) {
            for (MultipartFile multipartFile :
                    createItemRequest.getFiles()) {
                String fileAccessCode = Utils.createMD5(multipartFile.getOriginalFilename() + multipartFile.getContentType() + LocalDateTime.now());
                File file = File.builder().name(multipartFile.getOriginalFilename()).createdAt(LocalDateTime.now()).type(multipartFile.getContentType()).fileAccessCode(fileAccessCode).build();
                storageManager.uploadFile(multipartFile, fileAccessCode);
                fileManager.createFile(file);
                files.add(file);
            }
        }

        itemManager.createItem(createItemRequest, files, accessCode);
    }

    @GetMapping
    public List<ItemResponse> getItems(@Valid @RequestParam("code") GetAccessCodeRequest getAccessCodeRequest) {
        List<ItemResponse> itemsResponse = itemManager.getItemsByCode(getAccessCodeRequest).stream().map(item ->
                modelMapperManager.forItemResponse().map(item, ItemResponse.class)
        ).toList();
        return itemsResponse;
    }
}
