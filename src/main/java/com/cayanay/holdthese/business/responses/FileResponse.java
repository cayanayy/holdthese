package com.cayanay.holdthese.business.responses;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {
    private String fileCode;
    private String type;
    private String name;
}
