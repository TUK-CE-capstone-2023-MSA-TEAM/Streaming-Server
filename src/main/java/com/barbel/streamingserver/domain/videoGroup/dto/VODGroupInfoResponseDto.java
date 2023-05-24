package com.barbel.streamingserver.domain.videoGroup.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VODGroupInfoResponseDto {
  private String id;      // VOD 그룹 ID
  private String vodGroupName;   // VOD 그룹 이름
  private String thumbnailURL;    // 썸네일 URL
  private int vodCount;          // VOD 개수
}
