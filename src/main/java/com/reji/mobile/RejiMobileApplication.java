package com.reji.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.autoconfigure.*;

@EnableAsync
@EnableAutoConfiguration
@SpringBootApplication
public class RejiMobileApplication {

	public static void main(String[] args) {
		System.setProperty("server.connection-timeout","600000");

		SpringApplication.run(RejiMobileApplication.class, args);
	}
}
