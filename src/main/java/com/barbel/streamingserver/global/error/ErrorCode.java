package com.barbel.streamingserver.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  // Global
  INVALID_INPUT_VALUE(400, "G001", "유효하지 않은 입력값입니다."),
  INTERNAL_SERVER_ERROR(500, "G002", "서버 내부 오류입니다."),
  FILE_CONVERT_ERROR(500, "G003", "파일 변환 오류입니다."),
  UN_AUTHORIZED_ACCESS(401, "G004", "권한이 없습니다."),
  INVALID_TOKEN(401, "G005", "유효하지 않은 토큰입니다."),

  //VODGroup
  VODGROUP_NOT_FOUND(404, "V001", "VOD 그룹을 찾을 수 없습니다."),

  //VOD
  VOD_NOT_FOUND(404, "V002", "VOD를 찾을 수 없습니다."),

  ;

  private final int status;
  private final String code;
  private final String message;

}
