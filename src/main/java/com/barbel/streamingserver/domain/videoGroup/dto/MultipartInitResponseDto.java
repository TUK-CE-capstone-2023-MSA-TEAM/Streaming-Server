package com.barbel.streamingserver.domain.videoGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MultipartInitResponseDto {
    private String uploadId;
    private String URL;
}
