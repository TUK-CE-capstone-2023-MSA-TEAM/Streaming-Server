package com.barbel.streamingserver.domain.vodPermission.document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "video_permission")
public class VODPermission {
  @Id
  private String _id;
  private String userId;      // 회원 ID
  private String vodGroupId;  // VOD 그룹 ID
  private String startDt;     // VOD 그룹 시청 시작일 (인강 구매일 | yyyy-mm-dd)
  private String endDt;       // VOD 그룹 시청 종료일 (인강 만료일 | yyyy-mm-dd)
}
