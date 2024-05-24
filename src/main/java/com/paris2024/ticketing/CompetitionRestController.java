package com.paris2024.ticketing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.CompetitionPlaces;
import service.CompetitionService;

@RestController
@RequestMapping("/competitions")
public class CompetitionRestController {

	@Autowired
	private CompetitionService competitionService;

	@GetMapping("/{id}/availablePlaces")
	public CompetitionPlaces getCompetitionAvailablePlaces(@PathVariable("id") long competitionId) {
		return competitionService.getAvailablePlaces(competitionId);
	}

}
