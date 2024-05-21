package com.paris2024.ticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import service.CompetitionService;
import service.CompetitionServiceImpl;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Paris2024TicketingApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Paris2024TicketingApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "sports");
	}

	@Bean
	CompetitionService competitionService() {
		return new CompetitionServiceImpl();
	}

}
