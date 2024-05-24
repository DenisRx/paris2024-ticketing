package com.paris2024.ticketing;

import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.Competition;
import domain.CompetitionPlaces;
import domain.Ticket;
import domain.TicketsPurchaseFormData;
import service.CompetitionRestService;
import service.CompetitionService;
import service.TicketService;
import validator.TicketsPurchaseValidation;

@Controller
@RequestMapping("competitions")
public class CompetitionController {

	@Autowired
	private CompetitionService competitionService;

	@Autowired
	private CompetitionRestService competitionRestService;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private TicketsPurchaseValidation ticketsPurchaseValidation;

	@GetMapping("{id}/purchase")
	public String showCompetitionTicketsPurchaseForm(@PathVariable("id") long competitionId, Model model) {
		Optional<Competition> competition = competitionService.getCompetitionById(competitionId);
		if (!competition.isPresent()) {
			// TODO: replace by 404 page
			return "redirect:/sports";
		}

		// TODO: Replace 3 by current user id
		List<Ticket> purchasedTickets = ticketService.getCompetitionTicketsByUserId(competition.get().getId(), 3);

		CompetitionPlaces competitionPlaces = competitionRestService.getCompetitionAvailablePlaces(competitionId);
		model.addAttribute("availablePlaces", competitionPlaces.getAvailablePlaces());
		model.addAttribute("competition", competition.get());
		model.addAttribute("purchasedTicketsCount", purchasedTickets.size());
		model.addAttribute("formData", new TicketsPurchaseFormData());

		return "ticketsPurchaseForm";
	}

	@PostMapping("{id}/purchase")
	public String onPurchaseSubmit(@PathVariable("id") long competitionId,
			@ModelAttribute("formData") TicketsPurchaseFormData formData, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		formData.setCompetitionId(competitionId);
		ticketsPurchaseValidation.validate(formData, result);

		if (result.hasErrors()) {
			Optional<Competition> competition = competitionService.getCompetitionById(competitionId);
			// TODO: Replace 3 by current user id
			List<Ticket> purchasedTickets = ticketService.getCompetitionTicketsByUserId(competition.get().getId(), 3);
			model.addAttribute("competition", competition.get());
			model.addAttribute("purchasedTicketsCount", purchasedTickets.size());

			return "ticketsPurchaseForm";
		}

		int ticketsCount = formData.getTicketsCount();
		// TODO: Replace 3 by current user id
		if (ticketService.purchaseTickets(competitionId, 3, ticketsCount) == null) {
			// TODO: display error
		}

		Optional<Competition> competition = competitionService.getCompetitionById(competitionId);
		redirectAttributes.addFlashAttribute("purchasedTicketsCount", ticketsCount);

		return "redirect:/sports/" + competition.get().getSport().getId();
	}
}
