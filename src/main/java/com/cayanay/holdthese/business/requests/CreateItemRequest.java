package com.cayanay.holdthese.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemRequest {
    @NotNull
    @Size(min = 1, max = 32)
    private String code;

    @Null
    private List<MultipartFile> files;

    @NotNull
    @NotBlank
    @Size(min = 1)
    private String message;

    private Long duration;
}
