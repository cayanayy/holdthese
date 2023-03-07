package com.cayanay.holdthese.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAccessCodeRequest {
    @NotNull
    @Size(min = 1, max = 32)
    private String code;
}
