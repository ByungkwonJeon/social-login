package com.bk.oauth2.sociallogin.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionType {

  INVALID_FIELD_VALUE(
      HttpStatus.BAD_REQUEST, "Parameter passed for field is not meeting its required format."),

  NOT_FOUND(HttpStatus.NOT_FOUND, "Item is not found for specified id.");
  private final HttpStatus status;
  private final String description;

}
