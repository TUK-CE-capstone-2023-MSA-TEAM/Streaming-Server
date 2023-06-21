package com.barbel.streamingserver.domain.videoGroup.document;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Document(collection = "video_group")
public class VODGroup {
  @Id
  private String _id;
  private String ownerId;       // 소유자 ID
  private String vodGroupName;  // VOD 그룹 이름 (인강 시리즈 제목)
  private int vodCount;      // VOD 그룹에 포함된 VOD 갯수
  private String thumbnailURL; // 썸네일 이미지 URL
  private List<VOD> VODList;   // VOD 리스트
  private String keyword;       // 검색 키워드
}
