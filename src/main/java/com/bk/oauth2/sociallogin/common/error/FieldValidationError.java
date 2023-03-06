package com.bk.oauth2.sociallogin.common.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(onConstructor = @__({@JsonCreator}))
public class FieldValidationError {
  // the field whose value was rejected
  @JsonProperty("fieldName")
  private String fieldName;

  // the value that was rejected
  @JsonProperty("rejectedValue")
  private Object rejectedValue;

  // reason why the field is invalid
  @JsonProperty("message")
  private String message;

  public static void throwExceptionIfInvalidField(
      Stream<Collection<FieldValidationError>> errorStream) throws BusinessException {
    List<FieldValidationError> errors =
        errorStream.flatMap(Collection::stream).collect(Collectors.toList());
    if (errors.size() > 0) {
      throw new BusinessException(errors);
    }
  }
}
