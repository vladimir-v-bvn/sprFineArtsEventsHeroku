package com.vber.sprFineArtsEvents.service;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vber.sprFineArtsEvents.dto.AddYourEventDto;
import com.vber.sprFineArtsEvents.repository.EventRepositoryJdbc;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EventsService {

//private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EventsService.class);
  
  private final EventRepositoryJdbc eventRepositoryJdbc;
//@Autowired
//private  EventRepositoryJdbc eventRepositoryJdbc;

  public String vTopEvents() {
    return eventRepositoryJdbc.vTopEvents();
  }
  
  public int vTopEventsCount() {
    return eventRepositoryJdbc.vTopEventsCount();
  }
  
  public int vTopEventsCountStub() {
    return 6;
  }

  public Map<String, Object> addYourEvent(AddYourEventDto addYourEventDto) {
    return eventRepositoryJdbc.addYourEvent(addYourEventDto);
  }
  
}
