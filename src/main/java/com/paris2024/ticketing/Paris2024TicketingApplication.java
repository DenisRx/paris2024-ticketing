package com.paris2024.ticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Paris2024TicketingApplication {

	public static void main(String[] args) {
		SpringApplication.run(Paris2024TicketingApplication.class, args);
	}

}
