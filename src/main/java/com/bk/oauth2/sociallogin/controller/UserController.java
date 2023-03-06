package com.bk.oauth2.sociallogin.controller;


import com.bk.oauth2.sociallogin.common.error.ApiResponse;
import com.bk.oauth2.sociallogin.common.error.BusinessException;
import com.bk.oauth2.sociallogin.controller.apitype.ApiUser;
import com.bk.oauth2.sociallogin.controller.apitype.ApiUserRequest;
import com.bk.oauth2.sociallogin.controller.mapper.UserMapper;
import com.bk.oauth2.sociallogin.security.MyJwtAuthenticationToken;
import com.bk.oauth2.sociallogin.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RequestMapping("/api/users")
@Slf4j
@Validated
public class UserController {

  @NonNull private final UserService userService;

  @NonNull private final UserMapper userMapper;

  @GetMapping("/profile")
  public ApiResponse<ApiUser> getUserProfile(MyJwtAuthenticationToken token)
      throws BusinessException {

    log.debug(token.toString());

    return new ApiResponse<>(
        userMapper.toApi(userService.getUserProfile(token.getPrincipal().getUser().getId()))
    );
  }

  @PostMapping("/signup")
  public ApiResponse<ApiUser> signup(@RequestBody ApiUserRequest apiUserRequest)
      throws BusinessException {



    return new ApiResponse<>(
        userMapper.toApi(userService.createUser(userMapper.toModel(apiUserRequest)))
    );
  }

//  @PostMapping
//  public ApiResponse<ApiUser> createCard(
//      @Valid @RequestBody ApiUserRequest apiUserRequest)
//      throws BusinessException {
//
//    return new ApiResponse<>(
//        cardMapper.toApi(
//            cardService.createCard(
//                token.getPartyId(),
//                Constants.BAKKT_PARTNER_ID,
//                apiCardDesignRequest.getCardDesignId())));

}
