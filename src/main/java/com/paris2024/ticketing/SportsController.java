package com.paris2024.ticketing;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Sport;
import repository.SportRepository;

@Controller
@RequestMapping("/sports")
public class SportsController {

	@Autowired
	private SportRepository repository;

	@GetMapping
	public String showSports(Model model) {
		model.addAttribute("sports", repository.findAll());

		return "sportsList";
	}

	@GetMapping("/{id}")
	public String showSport(@PathVariable Long id, Model model) {
		Optional<Sport> sport = repository.findById(id);

		if (!sport.isPresent()) {
			return "redirect:/sports";
		}

		model.addAttribute("sport", sport.get());

		return "sportDetails";
	}
}
