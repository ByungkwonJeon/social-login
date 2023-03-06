package com.bk.oauth2.sociallogin.controller.apitype;

import com.bk.oauth2.sociallogin.data.enums.Role;
import com.bk.oauth2.sociallogin.data.enums.SocialType;
import com.bk.oauth2.sociallogin.data.enums.UserState;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor(onConstructor = @__({@JsonCreator}))
@NoArgsConstructor
@SuperBuilder
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
public class ApiUser {

  private UUID id;

  private String socialId;

  private SocialType socialType;

  private String email;

  private String img;

  private String nickname;

  private Role role;

  private OffsetDateTime lastLoginDate;

  private UserState userState;

  @JsonProperty("createdDate")
  private OffsetDateTime created;

  @JsonProperty("updatedDate")
  private OffsetDateTime updated;
}
