package com.vber.sprFineArtsEvents.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddYourEventDto {

  private String eventName;
  private LocalDate eventDate;
  private String locationId;

}
