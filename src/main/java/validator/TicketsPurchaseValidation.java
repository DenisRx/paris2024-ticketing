package validator;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Competition;
import domain.Ticket;
import domain.TicketsPurchaseFormData;
import service.CompetitionService;
import service.TicketService;

public class TicketsPurchaseValidation implements Validator {

	@Autowired
	private CompetitionService competitionService;

	@Autowired
	private TicketService ticketService;

	@Override
	public boolean supports(Class<?> clazz) {
		return TicketsPurchaseFormData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TicketsPurchaseFormData formData = (TicketsPurchaseFormData) target;

		int newTicketsCount = formData.getTicketsCount();

		Optional<Competition> competiton = competitionService.getCompetitionById(formData.getCompetitionId());
		if (!competiton.isPresent()) {
			errors.reject("ticketsPurchase.validation.competition.creation.error");
			return;
		}

		if (newTicketsCount > competiton.get().getRemainingSeats()) {
			errors.rejectValue("ticketsCount", "ticketsPurchase.validation.competition.seats.notEnough");
		}

		// TODO: Replace 3 by current user id
		List<Ticket> competitionTickets = ticketService.getCompetitionTicketsByUserId(formData.getCompetitionId(), 3);
		if (newTicketsCount > 20 || competitionTickets.size() + newTicketsCount > 20) {
			errors.rejectValue("ticketsCount", "ticketsPurchase.validation.competition.limit");
		}

		// TODO: Replace 3 by current user id
		List<Ticket> allUserTickets = ticketService.getTicketsByUserId(3);
		if (newTicketsCount + allUserTickets.size() > 100) {
			errors.rejectValue("ticketsCount", "ticketsPurchase.validation.competitions.limit");
		}

	}

}
