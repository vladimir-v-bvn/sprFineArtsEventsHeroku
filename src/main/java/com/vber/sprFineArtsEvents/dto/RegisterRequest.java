package com.vber.sprFineArtsEvents.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class RegisterRequest {
  
  @NotBlank(message = "User name may not be blank")
  private String userName;
  @NotBlank(message = "Password name may not be blank")
  private String password;
  @NotBlank(message = "Email may not be blank")
  @NotEmpty(message = "Email may not be empty")
  private String email;

  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
}
