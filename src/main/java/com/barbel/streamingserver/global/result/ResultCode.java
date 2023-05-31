package com.barbel.streamingserver.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

  // Global
  MEMBER_AUTHOR_SUCCESS("G001", "회원 인증 성공"),

  //VODGroup
  VODGROUP_REGISTRATION_SUCCESS("V001", "VOD 그룹 등록 성공"),
  VODGROUP_UPDATE_SUCCESS("V002", "VOD 그룹 수정 성공"),
  VODGROUP_SEARCH_SUCCESS("V003", "VOD 그룹 조회 성공"),
  VODGROUP_DELETE_SUCCESS("V004", "VOD 그룹 삭제 성공"),
  VODGROUP_LIST_REQUEST_SUCCESS("V005", "VOD 그룹 리스트 조회 성공"),

  //VOD
  VOD_REGISTRATION_INIT_SUCCESS("V006", "VOD 등록 초기화 성공"),
  VOD_MULTIPART_UPLOAD_SUCCESS("V007", "VOD 멀티파트 업로드 성공"),
  VOD_UPLOAD_COMPLATE("V007", "VOD 업로드 완료"),

  ;


  private final String code;
  private final String message;

}
