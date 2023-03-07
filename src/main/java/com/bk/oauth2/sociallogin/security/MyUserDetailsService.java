package com.bk.oauth2.sociallogin.security;

import com.bk.oauth2.sociallogin.data.model.User;
import com.bk.oauth2.sociallogin.data.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  public MyUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public UserPrincipal loadUserByUsername(final String username) {
    final User user = userRepository.findBySocialId(username);
    return new UserPrincipal(user);
  }
}