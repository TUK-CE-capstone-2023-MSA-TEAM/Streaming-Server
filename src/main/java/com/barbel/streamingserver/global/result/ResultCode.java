package com.barbel.streamingserver.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

  // Global
  MEMBER_AUTHOR_SUCCESS("G001", "회원 인증 성공");

  //VIDEO


  private final String code;
  private final String message;

}
