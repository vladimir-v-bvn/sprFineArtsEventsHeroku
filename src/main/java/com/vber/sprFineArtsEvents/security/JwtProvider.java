package com.vber.sprFineArtsEvents.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.vber.sprFineArtsEvents.exception.sprFineArtsEventsException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.SignatureException;

@Service
public class JwtProvider {

//keytool -genkey -alias sprFineArtsEvents -keyalg RSA -keystore sprFineArtsEvents.jks -keysize 2048
//password: trtD#94 name: vber --- CN=vber, OU=Unknown, O=Unknown, L=Unknown, ST=Unknown, C=Unknown
//keytool -list -storetype JKS -keystore sprFineArtsEvents.jks -storepass trtD#94

  private org.slf4j.Logger LOG = LoggerFactory.getLogger(JwtProvider.class);
  
//@Value("${jwt.expiration.time}")
  private Long jwtExpirationInMillis = 9876543210L;
  
//private Key key;
  private KeyStore keyStore;

  @PostConstruct
  public void init() {
  //key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    try {
      keyStore = KeyStore.getInstance("JKS");
      InputStream resourceAsStream = getClass().getResourceAsStream("/sprFineArtsEvents.jks");
      keyStore.load(resourceAsStream, "trtD#94".toCharArray());
    } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
        throw new sprFineArtsEventsException("Error loading keystore");
    }
  }
  
  public String generateToken(Authentication authenticate) {
    User principal = (User)authenticate.getPrincipal();
    try {
      return Jwts.builder()
                 .setSubject(principal.getUsername())
               //.signWith(key)
                 .signWith(getPrivateKey())
                 .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                 .compact();
    } catch (InvalidKeyException e) {
        throw new sprFineArtsEventsException("Error building Jwt from privateKey");
    }
  }

  private Key getPrivateKey() {
    try {
      return (PrivateKey) keyStore.getKey("sprFineArtsEvents", "trtD#94".toCharArray());
    } catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
        throw new sprFineArtsEventsException("Error getting privateKey");
    }
  }  

  private Key getPublicKey() {
    try {
      return keyStore.getCertificate("sprFineArtsEvents").getPublicKey();
    } catch (KeyStoreException e) {
        throw new sprFineArtsEventsException("Error getting publicKey");
    }
  }  

  @SuppressWarnings("deprecation")
  public boolean validateToken(String jwt) {
    try {
    //Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
      Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
      return true;
    }
    catch (io.jsonwebtoken.security.SignatureException e){
      LOG.error("JWT validation failed: " + e.getMessage());
      return false;
    }
  }

  @SuppressWarnings("deprecation")
  public String getUsernameFromJWT(String token) {
    Claims claims = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }

  public Long getjwtExpirationInMillis() {
    return jwtExpirationInMillis;
  }

}
