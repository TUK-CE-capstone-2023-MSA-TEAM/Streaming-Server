package com.barbel.streamingserver.global.error.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
  private final ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
