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
import service.DisciplineService;
import service.DisciplineServiceImpl;
import service.SportService;
import service.SportServiceImpl;
import service.StageService;
import service.StageServiceImpl;
import service.TicketService;
import service.TicketServiceImpl;
import validator.CompetitionCreationValidation;
import validator.TicketsPurchaseValidation;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Paris2024TicketingApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Paris2024TicketingApplication.class, args);
	}

	/*
	 * Routing
	 */

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "sports");
	}

	/*
	 * Services
	 */

	@Bean
	CompetitionService competitionService() {
		return new CompetitionServiceImpl();
	}

	@Bean
	SportService sportService() {
		return new SportServiceImpl();
	}

	@Bean
	TicketService ticketService() {
		return new TicketServiceImpl();
	}

	@Bean
	StageService stageService() {
		return new StageServiceImpl();
	}

	@Bean
	DisciplineService disciplineService() {
		return new DisciplineServiceImpl();
	}

	/*
	 * Validation
	 */

	@Bean
	CompetitionCreationValidation competitionCreationValidation() {
		return new CompetitionCreationValidation();
	}

	@Bean
	TicketsPurchaseValidation ticketsPurchaseValidation() {
		return new TicketsPurchaseValidation();
	}

}
