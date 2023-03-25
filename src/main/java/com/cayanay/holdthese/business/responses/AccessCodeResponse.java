package com.cayanay.holdthese.business.responses;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessCodeResponse {
    private String code;
    private LocalDateTime expiresAt;
    private List<ItemResponse> items;
}
