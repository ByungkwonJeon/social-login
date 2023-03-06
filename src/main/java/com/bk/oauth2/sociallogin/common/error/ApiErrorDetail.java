package com.bk.oauth2.sociallogin.common.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__({@JsonCreator}))
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
public class ApiErrorDetail {
  @JsonProperty("code")
  private String code;

  @JsonProperty("field")
  private String field;

  @JsonProperty("message")
  private String message;

  @JsonProperty("rejectedValue")
  private Object rejectedValue;
}
