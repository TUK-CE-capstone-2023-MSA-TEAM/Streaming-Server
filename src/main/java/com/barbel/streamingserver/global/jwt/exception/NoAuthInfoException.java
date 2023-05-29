package com.barbel.streamingserver.global.jwt.exception;


import com.barbel.streamingserver.global.error.ErrorCode;
import com.barbel.streamingserver.global.error.exception.BusinessException;

public class NoAuthInfoException extends BusinessException {
  public NoAuthInfoException() {
    super(ErrorCode.UN_AUTHORIZED_ACCESS);
  }
}
