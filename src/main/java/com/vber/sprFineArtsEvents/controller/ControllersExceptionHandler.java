package com.vber.sprFineArtsEvents.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice
public class ControllersExceptionHandler {
  
  private org.slf4j.Logger LOG = LoggerFactory.getLogger(ControllersExceptionHandler.class);
  
  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseStatusException exceptionHandler(Exception e) {
    LOG.info("ControllersExceptionHandler " + e.getClass());
    return new ResponseStatusException(HttpStatus.BAD_REQUEST, "A message according business logic.", e);
  }
}