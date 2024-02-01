package com.cardapi.cardapi;

import com.cardapi.cardapi.usecases.cardcost.InitOthersCost;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class CardApiApplication {

	private final InitOthersCost initOthersCost;

	public static void main(String[] args) {
		SpringApplication.run(CardApiApplication.class, args);

	}

	@EventListener(ApplicationReadyEvent.class)
	public void afterReady(){
		initOthersCost.command();
	}

}


