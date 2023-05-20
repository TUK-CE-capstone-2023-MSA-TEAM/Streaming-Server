package com.barbel.streamingserver.domain.vodViewInfo.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class VODViewInfo {
  @Id
  private String _id;
  private String userId;        // 회원 ID
  private String vodGroupId;    // VOD 그룹 ID
  private String vodIdx;        // VOD 인덱스
  private int lastModifiedTime; // 마지막 시청 위치 (초)
}
