package com.vber.sprFineArtsEvents.service;

//import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vber.sprFineArtsEvents.dto.AddYourEventDto;
import com.vber.sprFineArtsEvents.repository.EventRepositoryJdbc;

@ExtendWith(MockitoExtension.class)
public class EventsServiceTest {

//private EventRepositoryJdbc eventRepositoryJdbc = Mockito.mock(EventRepositoryJdbc.class);
  @Mock
  private EventRepositoryJdbc eventRepositoryJdbc;
  @Captor
  private ArgumentCaptor<AddYourEventDto> addYourEventDtoArgumentCaptor;
  
  @BeforeEach
  public void setup() {
    
  }
  
  @Test
  @DisplayName("addYourEventIsAdded")
  void addYourEventIsAdded() {
    EventsService eventsService = new EventsService(eventRepositoryJdbc);
    AddYourEventDto addYourEventDto = new AddYourEventDto ("cccc cccc", LocalDate.of(2022, Month.JANUARY, 01), "12345678");
    eventsService.addYourEvent(addYourEventDto);
    Mockito.verify(eventRepositoryJdbc, Mockito.times(1)).addYourEvent(ArgumentMatchers.any(AddYourEventDto.class));
    Mockito.verify(eventRepositoryJdbc, Mockito.times(1)).addYourEvent(addYourEventDtoArgumentCaptor.capture());
    Assertions.assertThat(addYourEventDtoArgumentCaptor.getValue().getEventName()).isEqualTo("cccc cccc");
    Assertions.assertThat(addYourEventDtoArgumentCaptor.getValue().getEventDate()).isEqualTo(LocalDate.of(2022, Month.JANUARY, 01));
    Assertions.assertThat(addYourEventDtoArgumentCaptor.getValue().getLocationId()).isEqualTo("12345678");
  }

  @Test
  @DisplayName("vTopEventsCountEqualsSixStub")
  void vTopEventsCountEqualsSixStub() {
    EventsService eventsService = new EventsService(null);
  //assertEquals(eventsService.vTopEventsCountStub(), 6);
  //fail("Not yet implemented");
  }

  @Test
  @DisplayName("vTopEventsCountEqualsSixMock")
  void vTopEventsCountEqualsSixMock() {
    
    final int topEventsCount = 6;
    
    EventsService eventsService = new EventsService(eventRepositoryJdbc);
    
    Integer expectedTopEventsCount = Integer.valueOf(topEventsCount);
    Mockito.when(eventRepositoryJdbc.vTopEventsCount()).thenReturn(expectedTopEventsCount);
    
    Integer actualTopEventsCount = eventsService.vTopEventsCount();
    
    Assertions.assertThat(expectedTopEventsCount.equals(actualTopEventsCount));
    
  //assertEquals(eventsService.vTopEventsCountStub(), 6);
  //assertTrue(true);
  }

}
