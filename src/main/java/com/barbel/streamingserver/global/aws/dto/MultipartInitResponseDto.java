package com.barbel.streamingserver.global.aws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MultipartInitResponseDto {
    private String uploadId;
    private String URL;
}
