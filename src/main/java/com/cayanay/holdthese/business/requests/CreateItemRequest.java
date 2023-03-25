package com.cayanay.holdthese.business.requests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemRequest {
    @Nullable
    private List<MultipartFile> files;

    @NotNull
    @Size(min = 1)
    private String message;

    private Long duration;
}
