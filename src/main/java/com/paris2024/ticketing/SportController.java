package com.paris2024.ticketing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Competition;
import domain.CompetitionCreationFormData;
import domain.Sport;
import domain.Ticket;
import jakarta.validation.Valid;
import service.CompetitionService;
import service.DisciplineService;
import service.SportService;
import service.StageService;
import service.TicketService;
import validator.CompetitionCreationValidation;

@Controller
@RequestMapping("/sports")
public class SportController {

	@Autowired
	private SportService sportService;

	@Autowired
	private CompetitionService competitionService;

	@Autowired
	private DisciplineService disciplineService;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private StageService stageService;

	@Autowired
	private CompetitionCreationValidation competitionCreationValidation;

	@GetMapping
	public String showSports(Model model) {
		model.addAttribute("sports", sportService.getSports());

		return "sportsList";
	}

	@GetMapping("/{id}")
	public String showSport(@PathVariable long id, Model model) {
		Optional<Sport> sport = sportService.getSportById(id);

		if (!sport.isPresent()) {
			// TODO: replace by 404 page
			return "redirect:/sports";
		}

		List<Competition> competitions = competitionService.getCompetitionsBySportId((long) id);
		// TODO: replace 3 by current user id
		List<Ticket> tickets = ticketService.getTicketsByUserId((long) 3);
		Map<Long, Long> competitionTicketCounts = new HashMap<>();

		for (Competition competition : competitions) {
			long count = tickets.stream().filter(t -> t.getCompetition().getId() == competition.getId()).count();
			competitionTicketCounts.put(competition.getId(), count);
		}

		model.addAttribute("sport", sport.get());
		model.addAttribute("competitions", competitions);
		model.addAttribute("competitionTicketCounts", competitionTicketCounts);

		return "sportDetails";
	}

	@GetMapping("/{id}/newCompetition")
	public String showCompetitionForm(@PathVariable long id, Model model) {
		model.addAttribute("stageList", stageService.getStagesOrderByName());
		model.addAttribute("disciplineList", disciplineService.getDisciplines());
		model.addAttribute("formData", new CompetitionCreationFormData());

		return "competitionForm";
	}

	@PostMapping("/{id}/newCompetition")
	public String onCompetitionSubmit(@PathVariable Long id,
			@Valid @ModelAttribute("formData") CompetitionCreationFormData formData, BindingResult result,
			Model model) {

		competitionCreationValidation.validate(formData, result);

		if (result.hasErrors()) {
			model.addAttribute("stageList", stageService.getStagesOrderByName());
			model.addAttribute("disciplineList", disciplineService.getDisciplines());

			return "competitionForm";
		}

		if (competitionService.createCompetition(id, formData) == null) {
			result.reject("competition.creation.failed", "{newCompetiton.creation.failed}");
			model.addAttribute("stageList", stageService.getStagesOrderByName());
			model.addAttribute("disciplineList", disciplineService.getDisciplines());

			return "competitionForm";
		}

		return "redirect:/sports/" + id;
	}
}
