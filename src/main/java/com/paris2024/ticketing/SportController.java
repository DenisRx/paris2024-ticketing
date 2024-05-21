package com.paris2024.ticketing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Competition;
import domain.Sport;
import domain.Ticket;
import repository.CompetitionRepository;
import repository.DisciplineRepository;
import repository.SportRepository;
import repository.StageRepository;
import repository.TicketRepository;
import service.CompetitionService;

@Controller
@RequestMapping("/sports")
public class SportController {

	@Autowired
	private SportRepository sportRepository;

	@Autowired
	private CompetitionRepository competitionRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private StageRepository stageRepository;

	@Autowired
	private DisciplineRepository disciplineRepository;

	@Autowired
	private CompetitionService competitionService;

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

		List<Competition> competitions = competitionRepository.findAllBySportId((long) id);
		List<Ticket> tickets = ticketRepository.findAllByUserId((long) 3);
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
		model.addAttribute("stageList", stageRepository.findAll());
		model.addAttribute("disciplineList", disciplineRepository.findAll());
		model.addAttribute("formData", new CompetitionCreationFormData());

		return "competitionForm";
	}

	@PostMapping("/{id}/newCompetition")
	public String onCompetitionSubmit(@PathVariable Long id,
			@ModelAttribute("formData") CompetitionCreationFormData formData, Model model) {

		competitionService.createCompetition(id, formData);

		return "redirect:/sports/" + id;
	}
}
