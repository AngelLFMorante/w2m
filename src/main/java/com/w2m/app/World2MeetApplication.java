package com.w2m.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.w2m.app")
@EnableCaching
public class World2MeetApplication {

	public static void main(String[] args) {
		SpringApplication.run(World2MeetApplication.class, args);
	}

}
