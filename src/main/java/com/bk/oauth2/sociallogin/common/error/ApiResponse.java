package com.bk.oauth2.sociallogin.common.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__({@JsonCreator}))
@JsonInclude(value = Include.NON_NULL, content = Include.NON_NULL)
public class ApiResponse<T> {

  private static final ApiResponse<Void> SUCCESS_VOID =
      ApiResponse.<Void>builder().success(true).build();

  @JsonProperty("success")
  private boolean success;

  @JsonProperty("payload")
  private T payload;

  @JsonProperty("error")
  private ApiError error;

  @JsonProperty("message")
  private String message;

  public ApiResponse(T payload) {
    this.success = true;
    this.payload = payload;
  }

  public ApiResponse(T payload, String message) {
    this.success = true;
    this.payload = payload;
    this.message = message;
  }

  public ApiResponse(ApiError error) {
    this.success = false;
    this.error = error;
  }

  public static ApiResponse<Void> voidApiResponse() {
    return SUCCESS_VOID;
  }

  public static <T> ApiResponse<T> failure(@NonNull String code, @NonNull String message) {
    return new ApiResponse<>(ApiError.builder().code(code).message(message).build());
  }
}
