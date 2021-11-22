package com.vber.sprFineArtsEvents.controller;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vber.sprFineArtsEvents.dto.AuthenticationResponse;
import com.vber.sprFineArtsEvents.dto.LoginRequest;
import com.vber.sprFineArtsEvents.dto.RefreshTokenRequest;
import com.vber.sprFineArtsEvents.dto.RegisterRequest;
import com.vber.sprFineArtsEvents.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  @Autowired
  private AuthService authService;
//private org.slf4j.Logger LOG = LoggerFactory.getLogger(AuthController.class);
  
  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
    authService.signup(registerRequest);
    return new ResponseEntity<String>(HttpStatus.OK);
  }

  @PostMapping("/login")
  public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }

  @PostMapping("refresh/token")
  public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
    return authService.refreshTokens(refreshTokenRequest);
  }

  @GetMapping()
  public String sss() {
    return "sss";
  }
}
