package com.bk.oauth2.sociallogin.security;

import java.util.Collection;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class MyJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  private Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

  private String principalClaimName = JwtClaimNames.SUB;

  private static MyUserDetailsService myUserDetailsService;

  @Override
  public final AbstractAuthenticationToken convert(Jwt jwt) {
    UserPrincipal userPrincipal = myUserDetailsService.loadUserByUsername(jwt.getSubject());
    Collection<GrantedAuthority> authorities = userPrincipal.getUser() != null
        ? (Collection<GrantedAuthority>) userPrincipal.getAuthorities() : extractAuthorities(jwt);

    String principalClaimValue = jwt.getClaimAsString(this.principalClaimName);
    return new MyJwtAuthenticationToken(jwt, userPrincipal.getUser() != null ? userPrincipal : jwt,
        authorities, principalClaimValue);
  }

  @Deprecated
  protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
    return this.jwtGrantedAuthoritiesConverter.convert(jwt);
  }

  /**
   * Sets the principal claim name. Defaults to {@link JwtClaimNames#SUB}.
   *
   * @param principalClaimName The principal claim name
   * @since 5.4
   */
  public void setPrincipalClaimName(String principalClaimName) {
    Assert.hasText(principalClaimName, "principalClaimName cannot be empty");
    this.principalClaimName = principalClaimName;
  }

  public static void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
    MyJwtAuthenticationConverter.myUserDetailsService = myUserDetailsService;
  }
}