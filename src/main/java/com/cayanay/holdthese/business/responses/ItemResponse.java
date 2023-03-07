package com.cayanay.holdthese.business.responses;

import jakarta.validation.constraints.Null;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {
    private String message;
    private LocalDateTime unableAt;

    @Null
    private List<FileResponse> files;
}
