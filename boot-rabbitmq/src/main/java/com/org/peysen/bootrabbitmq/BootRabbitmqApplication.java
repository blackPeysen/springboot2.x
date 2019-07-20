package com.org.peysen.bootrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BootRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootRabbitmqApplication.class, args);
	}

}
