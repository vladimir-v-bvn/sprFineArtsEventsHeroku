package com.vber.sprFineArtsEvents.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest {
  private String userName;
  @NotBlank
  private String refreshTokenRequest;

}
