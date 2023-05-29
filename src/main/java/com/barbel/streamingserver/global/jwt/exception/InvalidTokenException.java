package com.barbel.streamingserver.global.jwt.exception;


import com.barbel.streamingserver.global.error.ErrorCode;
import com.barbel.streamingserver.global.error.exception.BusinessException;

public class InvalidTokenException extends BusinessException {
  public InvalidTokenException() {
    super(ErrorCode.INVALID_TOKEN);
  }
}
