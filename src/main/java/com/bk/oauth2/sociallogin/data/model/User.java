package com.bk.oauth2.sociallogin.data.model;

import com.bk.oauth2.sociallogin.data.enums.Role;
import com.bk.oauth2.sociallogin.data.enums.SocialType;
import com.bk.oauth2.sociallogin.data.enums.UserState;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Slf4j
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"social_type", "social_id"}))
public class User extends Mutable {

  @NonNull
  @NotNull
  @Column(nullable = false)
  private String socialId;

  @NonNull
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SocialType socialType;

  @NonNull
  @NotNull
  @Column(nullable = false)
  private String email;

  private String img;

  @Column(nullable = false)
  private String nickname;

  @NonNull
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  private OffsetDateTime lastLoginDate;

  @NonNull
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserState userState;
}