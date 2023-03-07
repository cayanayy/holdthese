package com.cayanay.holdthese.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadFileRequest {
    @NotNull
    @NotBlank
    @Size(min = 1)
    private String accessCode;
}
