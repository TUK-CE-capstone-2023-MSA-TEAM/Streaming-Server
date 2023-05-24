package com.barbel.streamingserver.domain.videoGroup.dto;


import lombok.Data;

@Data
public class VODGroupNameUpdateRequestDto {
  private String vodGroupId;      // VOD 그룹 ID
  private String vodGroupName;   // VOD 그룹 이름
}
