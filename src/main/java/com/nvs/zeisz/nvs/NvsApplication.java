package com.nvs.zeisz.nvs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class NvsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NvsApplication.class, args);
	}

}
