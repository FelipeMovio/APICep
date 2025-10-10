package com.consumindoAPICep.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ConsumindoApiCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumindoApiCepApplication.class, args);
	}

}
