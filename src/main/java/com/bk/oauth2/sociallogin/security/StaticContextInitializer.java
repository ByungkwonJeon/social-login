package com.bk.oauth2.sociallogin.security;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaticContextInitializer {

  @Autowired
  private MyUserDetailsService myUserDetailsService;

  @PostConstruct
  public void init() {
    MyJwtAuthenticationConverter.setMyUserDetailsService(myUserDetailsService);
  }
}
