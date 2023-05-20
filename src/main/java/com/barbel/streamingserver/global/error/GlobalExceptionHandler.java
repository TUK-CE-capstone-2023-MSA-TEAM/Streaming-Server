package com.barbel.streamingserver.global.error;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.barbel.streamingserver.global.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleRuntimeException(BusinessException e) {
    final ErrorCode errorCode = e.getErrorCode();
    final ErrorResponse response = ErrorResponse.builder()
        .message(errorCode.getMessage())
        .code(errorCode.getCode())
        .build();
    log.warn(e.getMessage());
    return ResponseEntity.status(errorCode.getStatus()).body(response);
  }

  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
    log.warn(e.getMessage());
    return new ResponseEntity<>(response, BAD_REQUEST);
  }
}
