package com.barbel.streamingserver.domain.videoGroup.document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VOD {
  private int idx;
  private String vodName;       // VOD 제목
  private String vodType;       // VOD 동영상 파일 확장자
  private String vodURL;        // VOD 스토리지 URL
  private int vodLength;        // VOD 길이 (초)
}
