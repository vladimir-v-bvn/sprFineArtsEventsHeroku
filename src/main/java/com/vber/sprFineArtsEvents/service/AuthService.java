package com.vber.sprFineArtsEvents.service;

//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vber.sprFineArtsEvents.config.AppConfig;
import com.vber.sprFineArtsEvents.dto.AuthenticationResponse;
import com.vber.sprFineArtsEvents.dto.LoginRequest;
import com.vber.sprFineArtsEvents.dto.RefreshTokenRequest;
import com.vber.sprFineArtsEvents.dto.RegisterRequest;
import com.vber.sprFineArtsEvents.model.User;
import com.vber.sprFineArtsEvents.repository.UserRepository;
import com.vber.sprFineArtsEvents.security.JwtProvider;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtProvider jwtProvider;
  private final AppConfig appConfig;

//private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AuthService.class);

//@Transactional
  public void signup(RegisterRequest registerRequest) {
    User user = new User();
    user.setUserName(registerRequest.getUserName());
    user.setPassword(encodePassword(registerRequest.getPassword()));
    user.setEmail(registerRequest.getEmail());
  //log.info(user.toString());
    userRepository.save(user);
    sendMail();
  }

  @Async
  private void sendMail() {
  //appConfig.getUrl() + "" + token;
  //no e-mail confirmation
  //log.info("e-mail confirmation runs asynchronously");
  }
  
  public AuthenticationResponse login(LoginRequest loginRequest) {
    Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authenticate);
  //AuthenticationResponse authenticationResponse = new AuthenticationResponse(loginRequest.getUserName(), jwtProvider.generateToken(authenticate));
  //LOG.info(authenticationResponse.getUserName() + " " + authenticationResponse.getAuthenticationToken());
    return AuthenticationResponse.builder()
                                 .userName(loginRequest.getUserName())
                                 .authenticationToken(jwtProvider.generateToken(authenticate))
                                 .refreshToken("")
                                 .expiresAt(Instant.now().plusMillis(jwtProvider.getjwtExpirationInMillis()))
                                 .build();
  }

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

  public AuthenticationResponse refreshTokens(RefreshTokenRequest refreshTokenRequest) {
  //TODO
    return null;
  }

}
