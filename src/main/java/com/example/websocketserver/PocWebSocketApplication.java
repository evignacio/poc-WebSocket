package com.example.websocketserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class PocWebSocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocWebSocketApplication.class, args);
	}
}
