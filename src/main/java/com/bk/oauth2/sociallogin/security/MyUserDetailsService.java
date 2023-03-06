package com.bk.oauth2.sociallogin.security;

import com.bk.oauth2.sociallogin.data.model.User;
import com.bk.oauth2.sociallogin.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserPrincipal loadUserByUsername(final String username) {
    final User user = userRepository.findBySocialId(username);
    return new UserPrincipal(user);
  }
}