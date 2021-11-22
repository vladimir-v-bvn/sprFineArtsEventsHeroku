package com.vber.sprFineArtsEvents.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vber.sprFineArtsEvents.dto.AddYourEventDto;
import com.vber.sprFineArtsEvents.service.EventsService;
import com.vber.sprFineArtsEvents.util.rsJSONConversion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "localhost:8080")
public class EventsController {

  @Autowired
  EventsService eventsService;
//private org.slf4j.Logger LOG = LoggerFactory.getLogger(EventsController.class);
//LOG.info("EventsController");

  @GetMapping("/vtopevents")
  public ResponseEntity<String> vTopEvents() {
    return ResponseEntity.status(HttpStatus.OK).header("Custom-Header", "").body(eventsService.vTopEvents());
  }
 
  @GetMapping("/vyourevents")
  public String vYourEvents() {
//TODO:    eventsService.vYourEvents();
    return eventsService.vTopEvents();
  }

  @GetMapping("/vtopeventscount")
  public int vTopEventsCount() {
  //this method is only for tests
    return eventsService.vTopEventsCount();
  }
  @GetMapping("/vtopeventscountjson")
  public ResponseEntity<String> vTopEventsCountJson() {
  //this method is only for tests
  //log.info("s " + eventsService.vTopEventsCount());
    return ResponseEntity.status(HttpStatus.OK).body(rsJSONConversion.convertIntegerToJSONObject("i", eventsService.vTopEventsCount()));
  }

  @PostMapping("/addyourevent")
  public Map<String, Object> addYourEvent(@RequestBody AddYourEventDto addYourEventDto) {
    return eventsService.addYourEvent(addYourEventDto);
  }
//public Map<String, Object> addYourEvent(@RequestBody AddYourEventDto addYourEventDto, @RequestHeader Map<String, String> headers) {
  //headers.forEach((key, value) -> LOG.info(String.format("Header '%s' = %s", key, value)));
  //return eventsService.addYourEvent(addYourEventDto);
//}

}