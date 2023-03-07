package com.cayanay.holdthese.business.responses;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessCodeResponse {
    private String code;
    private LocalDateTime expiresAt;
}
