package com.vber.sprFineArtsEvents;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.vber.sprFineArtsEvents.config.SwaggerConfiguration;

@EnableAsync
@SpringBootApplication
@Import(SwaggerConfiguration.class)
//@EnableCaching(proxyTargetClass = true)
public class SprFineArtsEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprFineArtsEventsApplication.class, args);
	}

}
