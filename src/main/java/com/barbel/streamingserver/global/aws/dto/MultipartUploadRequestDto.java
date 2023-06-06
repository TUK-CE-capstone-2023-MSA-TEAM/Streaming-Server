package com.barbel.streamingserver.global.aws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class MultipartUploadRequestDto {
    private String uploadId;
    private int totalPart;
    private int multipartIndex;
    private String VODGroupId;
    private int vodIndex;
}
