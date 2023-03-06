package com.bk.oauth2.sociallogin.service;

import com.bk.oauth2.sociallogin.common.error.BusinessException;
import com.bk.oauth2.sociallogin.common.error.ExceptionType;
import com.bk.oauth2.sociallogin.controller.apitype.ApiUserRequest;
import com.bk.oauth2.sociallogin.data.enums.Role;
import com.bk.oauth2.sociallogin.data.enums.SocialType;
import com.bk.oauth2.sociallogin.data.enums.UserState;
import com.bk.oauth2.sociallogin.data.model.User;
import com.bk.oauth2.sociallogin.data.repository.UserRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserService {

  @NonNull
  private final UserRepository userRepository;


  public User getUserProfile(UUID userId) {
    return userRepository.findById(userId).orElseThrow(
        () ->
            new BusinessException(
                ExceptionType.NOT_FOUND,
                String.format("user not found with userId = %s", userId)));
  }

  public Optional<User> getUserProfile(SocialType socialType, String socialId) {
    return userRepository.findBySocialTypeAndSocialId(socialType, socialId);
  }

  public User createUser(User user) {
    user.setRole(Role.GUEST);
    user.setUserState(UserState.ACTIVE);
    return userRepository.save(user);

  }
}