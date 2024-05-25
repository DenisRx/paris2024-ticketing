package com.paris2024.ticketing;

import java.security.Principal;
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
	public String showCompetitionTicketsPurchaseForm(@PathVariable("id") long competitionId, Model model,
			Principal principal) {
		Optional<Competition> competition = competitionService.getCompetitionById(competitionId);
		if (competition.isEmpty()) {
			return "redirect:/sports";
		}

		List<Ticket> purchasedTickets = ticketService.getCompetitionTicketsByUserEmail(competition.get().getId(),
				principal.getName());

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
			RedirectAttributes redirectAttributes, Principal principal) {

		formData.setCompetitionId(competitionId);
		ticketsPurchaseValidation.validate(formData, result);

		String userEmail = principal.getName();

		if (result.hasErrors()) {
			Optional<Competition> competition = competitionService.getCompetitionById(competitionId);
			List<Ticket> purchasedTickets = ticketService.getCompetitionTicketsByUserEmail(competition.get().getId(),
					userEmail);

			CompetitionPlaces competitionPlaces = competitionRestService.getCompetitionAvailablePlaces(competitionId);
			model.addAttribute("availablePlaces", competitionPlaces.getAvailablePlaces());
			model.addAttribute("competition", competition.get());
			model.addAttribute("purchasedTicketsCount", purchasedTickets.size());

			return "ticketsPurchaseForm";
		}

		int ticketsCount = formData.getTicketsCount();
		if (ticketService.purchaseTickets(competitionId, userEmail, ticketsCount) == null) {
			result.reject("ticketsPurchase.creation.failed");

			Optional<Competition> competition = competitionService.getCompetitionById(competitionId);
			List<Ticket> purchasedTickets = ticketService.getCompetitionTicketsByUserEmail(competition.get().getId(),
					userEmail);

			CompetitionPlaces competitionPlaces = competitionRestService.getCompetitionAvailablePlaces(competitionId);
			model.addAttribute("availablePlaces", competitionPlaces.getAvailablePlaces());
			model.addAttribute("competition", competition.get());
			model.addAttribute("purchasedTicketsCount", purchasedTickets.size());

			return "ticketsPurchaseForm";
		}

		Optional<Competition> competition = competitionService.getCompetitionById(competitionId);
		redirectAttributes.addFlashAttribute("purchasedTicketsCount", ticketsCount);

		return "redirect:/sports/" + competition.get().getSport().getId();
	}
}
