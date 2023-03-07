package com.cayanay.holdthese.controllers;

import com.cayanay.holdthese.business.concretes.AccessCodeManager;
import com.cayanay.holdthese.business.requests.CreateAccessCodeRequest;
import com.cayanay.holdthese.business.requests.GetAccessCodeRequest;
import com.cayanay.holdthese.business.responses.AccessCodeResponse;
import com.cayanay.holdthese.core.utilities.mappers.ModelMapperManager;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/access")
@AllArgsConstructor
@CrossOrigin("*")
public class AccessCodeController {

    private final AccessCodeManager accessCodeManager;
    private final ModelMapperManager modelMapperManager;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createAccessCode(@Valid @RequestBody CreateAccessCodeRequest createAccessCodeRequest) {
        accessCodeManager.createAccessCode(createAccessCodeRequest);
    }

    @GetMapping
    public AccessCodeResponse getAccessCode(@Valid @RequestParam("code") GetAccessCodeRequest getAccessCodeRequest) {
        AccessCodeResponse accessCodeResponse = modelMapperManager.forResponses().map(accessCodeManager.getAccessCodeByCode(getAccessCodeRequest), AccessCodeResponse.class);
        return accessCodeResponse;
    }
}
