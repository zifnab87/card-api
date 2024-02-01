package com.cardapi.cardapi;

import com.cardapi.cardapi.helpers.ValueWrapper;
import com.cardapi.cardapi.usecases.countrycost.InitOthersCost;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class CardApiApplication {

	private final InitOthersCost initOthersCost;

	private final ValueWrapper valueWrapper;

	public static void main(String[] args) {
		SpringApplication.run(CardApiApplication.class, args);

	}

	@EventListener(ApplicationReadyEvent.class)
	public void afterReady() {
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objMapper = new ObjectMapper(new JsonFactory());
		objMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		return objMapper;
	}


}


