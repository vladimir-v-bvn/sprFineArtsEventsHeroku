package com.vber.sprFineArtsEvents.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.slf4j.LoggerFactory;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  private org.slf4j.Logger LOG = LoggerFactory.getLogger(WebConfig.class);
  
  @Override
  public void addCorsMappings (CorsRegistry corsRegistry) {

    corsRegistry.addMapping("/**")
              //.allowedOrigins("*")
                .allowedOriginPatterns("*")
              //.allowedOriginPatterns("https://*.domain.com")
                .allowedMethods("*")
                .maxAge(3600L)
                .allowedHeaders("*")
                .exposedHeaders("Autorization")
                .allowCredentials(true)
                ;

    }

}
