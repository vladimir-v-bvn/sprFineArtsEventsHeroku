package com.vber.sprFineArtsEvents.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;

import com.vber.sprFineArtsEvents.dto.AddYourEventDto;


@DataJpaTest 
//@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventRepositoryTest {
/*
  @Container
  MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest")
    .withDatabaseName("finearts")
    .withUsername("finearts")
    .withPassword("mysql");
    
  @Autowired private EventRepository eventRepository;
    
  @Test
  public void shouldSaveEvent() {
    AddYourEventDto addYourEventDto = new AddYourEventDto ("cccc cccc", LocalDate.of(2022, Month.JANUARY, 01), "12345678");
  //Map<String, Object> actualEventObject = eventRepository.addYourEvent(addYourEventDto);
  //assertThat(actualEventObject).usingRecursiveComparison().ignoringFields("eventId").isEqualTo(addYourEventDto);
  }
*/
}
