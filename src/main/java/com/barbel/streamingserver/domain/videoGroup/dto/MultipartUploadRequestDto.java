package com.barbel.streamingserver.domain.videoGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class MultipartUploadRequestDto {
    private String uploadId;
    private int totalPart;
    private int partIndex;
    private MultipartFile file;
    private String VODGroupId;
    private int vodIndex;
}
