package com.bk.oauth2.sociallogin.security;

import static com.bk.oauth2.sociallogin.security.CommonClaimNames.email;
import static com.bk.oauth2.sociallogin.security.CommonClaimNames.email_verified;
import static com.bk.oauth2.sociallogin.security.CommonClaimNames.iss;
import static com.bk.oauth2.sociallogin.security.CommonClaimNames.sub;

import com.bk.oauth2.sociallogin.data.enums.SocialType;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Slf4j
@Getter
public class SocialJwtAuthenticationToken extends JwtAuthenticationToken {

  private static final Pattern uuidPattern = Pattern.compile(
      "(?i)^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$");
  private String subjectId;
  private String emailAddress;
  private boolean emailVerified;
  private Instant issuedAt;
  private Instant expiresAt;
  private String issuer;
  private SocialType socialType;
  private String socialId;

  public SocialJwtAuthenticationToken(Jwt jwt) {
    this(jwt, Collections.emptyList(), jwt.getSubject());
  }

  public SocialJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
    this(jwt, authorities, jwt.getSubject());
  }

  public SocialJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities,
      String name) {
    this(jwt, authorities, null, name);
  }

  public SocialJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities,
      Map<String, Object> claims) {
    this(jwt, authorities, claims, jwt.getSubject());
  }

  public SocialJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities,
      Map<String, Object> claims, String name) {
    super(jwt, authorities, name);
    Map<String, Object> claimsMap = claims == null ? jwt.getClaims() : claims;
    this.subjectId = parseString(claimsMap, sub.name());
    this.emailAddress = parseString(claimsMap, email.name());
    this.emailVerified = parseBoolean(claimsMap, email_verified.name());
    this.issuedAt = jwt.getIssuedAt();
    this.expiresAt = jwt.getExpiresAt();
    this.issuer = parseString(claimsMap, iss.name());
    this.socialId = parseString(claimsMap, sub.name());

    if (this.issuer.contains("google")) {
      this.socialType = SocialType.GOOGLE;
    } else if (this.issuer.contains("facebook")) {
      this.socialType = SocialType.FACEBOOK;
    }

  }

  private String parseString(Map<String, Object> jwt, String claim) {
    return jwt.containsKey(claim) ? String.valueOf(jwt.get(claim)) : null;
  }

  private boolean parseBoolean(Map<String, Object> jwt, String claim) {
    if (jwt.containsKey(claim) && jwt.get(claim) != null) {
      if (jwt.get(claim) instanceof Boolean) {
        return (Boolean) jwt.get(claim);
      }
      return jwt.containsKey(claim) && jwt.get(claim) != null && Boolean.parseBoolean(
          (String) jwt.get(claim));
    }

    return false;
  }
}
