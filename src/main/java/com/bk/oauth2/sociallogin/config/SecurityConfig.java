package com.bk.oauth2.sociallogin.config;

import com.bk.oauth2.sociallogin.security.MyJwtIssuerAuthenticationManagerResolver;
import java.util.Collection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  private static final String rolesClaim = "ROLE_USER";

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    MyJwtIssuerAuthenticationManagerResolver authenticationManagerResolver = new MyJwtIssuerAuthenticationManagerResolver(
        "http://localhost:8080/auth/realms/SpringBootKeyClock", "https://accounts.google.com",
        "https://www.facebook.com");

    http.formLogin().disable().httpBasic().disable().authorizeRequests().antMatchers("/**")
        .authenticated().and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().oauth2ResourceServer()
        .authenticationManagerResolver(authenticationManagerResolver);
    return http.build();
  }

  // If JWT has authority
  private class JwtAccessTokenCustomizedConverter implements
      Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(final Jwt jwt) {

      String permissionList = jwt.getClaim(rolesClaim);
      return AuthorityUtils.commaSeparatedStringToAuthorityList(permissionList);
    }
  }


}