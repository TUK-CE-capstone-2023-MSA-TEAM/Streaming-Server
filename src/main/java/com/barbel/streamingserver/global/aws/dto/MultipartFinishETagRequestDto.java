package com.barbel.streamingserver.global.aws.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MultipartFinishETagRequestDto {
  private int partIndex;
  private String etag;
}
