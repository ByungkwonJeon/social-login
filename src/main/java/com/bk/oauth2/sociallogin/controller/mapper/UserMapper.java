package com.bk.oauth2.sociallogin.controller.mapper;


import com.bk.oauth2.sociallogin.controller.apitype.ApiUser;
import com.bk.oauth2.sociallogin.controller.apitype.ApiUserRequest;
import com.bk.oauth2.sociallogin.data.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

  ApiUser toApi(User user);

  User toModel(ApiUserRequest apiUserRequest);

}
