package com.paris2024.ticketing;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Sport;
import repository.CompetitionRepository;
import repository.SportRepository;

@Controller
@RequestMapping("/sports")
public class SportController {

	@Autowired
	private SportRepository sportRepository;

	@Autowired
	private CompetitionRepository competitionRepository;

	@GetMapping
	public String showSports(Model model) {
		model.addAttribute("sports", sportRepository.findAll());

		return "sportsList";
	}

	@GetMapping("/{id}")
	public String showSport(@PathVariable long id, Model model) {
		Optional<Sport> sport = sportRepository.findById(id);

		if (!sport.isPresent()) {
			// TODO: replace by 404 page
			return "redirect:/sports";
		}

		model.addAttribute("sportName", sport.get().getName());
		model.addAttribute("competitions", competitionRepository.findAllBySportId((long) id));

		return "sportDetails";
	}
}
