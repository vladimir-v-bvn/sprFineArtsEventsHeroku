package com.vber.sprFineArtsEvents.controller;

import static org.hamcrest.CoreMatchers.is;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.vber.sprFineArtsEvents.security.JwtProvider;
import com.vber.sprFineArtsEvents.service.AuthService;
import com.vber.sprFineArtsEvents.service.EventsService;


@WebMvcTest(controllers = EventsController.class)
public class EventsControllerTest {

  @MockBean
  private EventsService eventsService;
  @MockBean
  private UserDetailsService userDetailsService;
  @MockBean
  private JwtProvider jwtProvider;
  
  @Autowired
  private MockMvc mockMvc;
  
  @Test
  @DisplayName("vTopEventsCountEqualsSixControllerMockBean")
  public void vTopEventsCountEqualsSixControllerMockBean() throws Exception {
    
    final int topEventsCount = 6;
    final String url = "/api/v1/events/vtopeventscountjson";
    
    Integer expectedTopEventsCount = Integer.valueOf(topEventsCount);
  //Integer actualTopEventsCount = eventsService.vTopEventsCount();
    Mockito.when(eventsService.vTopEventsCount()).thenReturn(expectedTopEventsCount);

  //MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
    
    mockMvc.perform(MockMvcRequestBuilders.get(url))
           .andExpect(MockMvcResultMatchers.status().is(200))
           .andExpect(MockMvcResultMatchers.jsonPath("$.i", is(6)))
           ;
    
  }

}
