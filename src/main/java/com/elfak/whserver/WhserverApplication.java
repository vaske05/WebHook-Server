package com.elfak.whserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WhserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhserverApplication.class, args);
	}

}
