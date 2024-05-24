package com.paris2024.ticketing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Competition;
import service.CompetitionService;

@RestController
@RequestMapping("/sports")
public class SportRestController {

	@Autowired
	private CompetitionService competitionService;

	@GetMapping("/{id}/competitions")
	public List<Competition> getSportCompetitions(@PathVariable("id") long sportId) {
		return competitionService.getCompetitionsBySportId(sportId);
	}

}
