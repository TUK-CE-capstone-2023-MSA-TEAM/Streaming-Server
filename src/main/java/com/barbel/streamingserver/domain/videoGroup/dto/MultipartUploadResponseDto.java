package com.barbel.streamingserver.domain.videoGroup.dto;

import com.amazonaws.services.s3.model.PartETag;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MultipartUploadResponseDto {
    private PartETag partETag;

    public static MultipartUploadResponseDto empty() {
        return new MultipartUploadResponseDto(new PartETag(0, ""));
    }
}
