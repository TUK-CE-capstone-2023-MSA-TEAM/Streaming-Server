package com.barbel.streamingserver.global.error;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
  private String code;
  private String message;
  private List<FieldError> errors;

  private ErrorResponse(ErrorCode errorCode, List<FieldError> fieldErrors) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
    this.errors = fieldErrors;
  }

  private ErrorResponse(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
    this.errors = new ArrayList<>();
  }

  public static ErrorResponse of(final ErrorCode errorCode, final BindingResult bindingResult) {
    return new ErrorResponse(errorCode, FieldError.of(bindingResult));
  }

  @Getter
  @AllArgsConstructor
  public static class FieldError {
    private String field;
    private String value;
    private String reason;

    public static List<FieldError> of(final BindingResult bindingResult) {
      final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
      return fieldErrors.stream()
          .map(error ->
              new FieldError(error.getField(),
              error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
              error.getDefaultMessage()))
          .collect(Collectors.toList());
    }
  }
}
