package com.bk.oauth2.sociallogin.controller;

import static org.springframework.http.HttpStatus.OK;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@Slf4j
@Validated
public class DemoController {

  @GetMapping
  public ResponseEntity<String> sayHello(JwtAuthenticationToken token) {

    log.debug(token.toString());

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    log.debug(authentication.toString());


    return ResponseEntity.ok("Hell OAuth2");
  }
}