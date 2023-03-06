package com.bk.oauth2.sociallogin.common.error;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class BusinessException extends RuntimeException {
  private final ExceptionType exceptionType;
  private List<FieldValidationError> validationErrors;
  private String table;
  private List<ApiErrorDetail> errorDetail;
  private ApiResponse<?> apiResponse;
  private String internalNotes;

  public BusinessException(ExceptionType exceptionType) {
    super(exceptionType.toString());
    this.exceptionType = exceptionType;
  }

  public BusinessException(ExceptionType exceptionType, Throwable cause) {
    super(exceptionType.toString());
    this.exceptionType = exceptionType;
    initCause(cause);
  }

  public BusinessException(ExceptionType exceptionType, ApiResponse<?> apiResponse) {
    super(exceptionType.toString());
    this.exceptionType = exceptionType;
    this.apiResponse = apiResponse;
  }

  public BusinessException(ExceptionType exceptionType, String message) {
    super(message != null ? message : exceptionType.toString());
    this.exceptionType = exceptionType;
  }

  public BusinessException(ExceptionType exceptionType, String message, Throwable cause) {
    this(exceptionType, message);
    initCause(cause);
  }

  public BusinessException(ExceptionType exceptionType, ErrorCode errorCode) {
    super(exceptionType.toString());
    this.exceptionType = exceptionType;

    errorDetail = new ArrayList<>();
    ApiErrorDetail apiErrorDetail = new ApiErrorDetail(errorCode.name(), null, null, null);
    errorDetail.add(apiErrorDetail);
  }

  public BusinessException(
      ExceptionType exceptionType,
      String message,
      ErrorCode errorCode,
      String detailMessage,
      Throwable cause) {
    this(exceptionType, message, errorCode, detailMessage);
    initCause(cause);
  }

  public BusinessException(
      ExceptionType exceptionType, String message, ErrorCode errorCode, String detailMessage) {
    super(message != null ? message : exceptionType.toString());
    this.exceptionType = exceptionType;

    errorDetail = new ArrayList<>();
    detailMessage = detailMessage != null ? detailMessage : errorCode.toString();
    ApiErrorDetail apiErrorDetail = new ApiErrorDetail(errorCode.name(), null, detailMessage, null);
    errorDetail.add(apiErrorDetail);
  }

  public BusinessException(
      ExceptionType exceptionType, String message, List<ApiErrorDetail> errorDetail) {
    super(message != null ? message : exceptionType.toString());
    this.exceptionType = exceptionType;
    this.errorDetail = errorDetail;
  }

  public BusinessException(
      ExceptionType exceptionType,
      String message,
      List<ApiErrorDetail> errorDetail,
      Throwable cause) {
    this(exceptionType, message, errorDetail);
    initCause(cause);
  }

  public BusinessException(List<FieldValidationError> validationErrors) {
    super("validation error");
    this.exceptionType = ExceptionType.INVALID_FIELD_VALUE;
    this.validationErrors = validationErrors;
  }

  public BusinessException(Enum<?> table, UUID id) {
    super("failed to find " + table + " record with id " + id);
    this.exceptionType = ExceptionType.NOT_FOUND;
    this.table = table.name();
  }

  public BusinessException(Enum<?> table, String field, String value) {
    super("failed to find " + table + " record with " + field + " value " + value);
    this.exceptionType = ExceptionType.NOT_FOUND;
    this.table = table.name();
  }

  public BusinessException(Enum<?> table, Map<String, Object> keys) {
    super(
        "failed to find "
            + table
            + " record with "
            + keys.entrySet().stream().map(Object::toString).collect(joining("&")));
    this.exceptionType = ExceptionType.NOT_FOUND;
    this.table = table.name();
  }

  public BusinessException(ExceptionType exceptionType, UUID ticketId) {
    super("failed to find ticket with id " + ticketId.toString());
    this.exceptionType = exceptionType;
  }

  public String internalNotes() {
    return internalNotes;
  }

  public BusinessException internalNotes(String internalNotes) {
    this.internalNotes = internalNotes;
    return this;
  }
}
