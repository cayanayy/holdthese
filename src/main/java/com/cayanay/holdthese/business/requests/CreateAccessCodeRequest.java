package com.cayanay.holdthese.business.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccessCodeRequest {
    @NotNull
    @Size(min = 1, max = 32)
    private String code;

    private Long duration;
}
