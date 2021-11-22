package com.vber.sprFineArtsEvents.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Entity
@Validated
@Table(name = "fae_user")
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @Column(name = "user_name")
  @NotBlank(message = "User name may not be blank")
  private String userName;
  @Column(name = "password")
  @NotBlank(message = "Password may not be blank")
  private String password;
  @Column(name = "email", nullable = false)
  @Valid
  @NotBlank(message = "Email may not be blank")
  @NotEmpty(message = "Email may not be empty")
  @NotNull(message = "Email may not be null")
  private String email;
  
  @Override
  public String toString() {
    return userName + " | " + password + " | " +  (!(email == null || email.isEmpty()) ? email : "NullOrEmpty");
  }

}
