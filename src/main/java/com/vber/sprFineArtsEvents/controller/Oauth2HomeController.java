package com.vber.sprFineArtsEvents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Oauth2HomeController {
  
  @GetMapping("oauth2home")
  public String Oauth2Home() {
    return "oauth2home";
  }
}
