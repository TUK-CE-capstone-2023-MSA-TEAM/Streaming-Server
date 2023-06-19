package com.barbel.streamingserver.global.aws.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class MultipartUploadRequestDto {
    private String uploadId;
    private int totalPart;
    private int multipartIndex;
    private String vodGroupId;
    private int vodIndex;
}
