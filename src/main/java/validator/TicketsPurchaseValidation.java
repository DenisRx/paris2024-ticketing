package validator;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
		String userEmail = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			userEmail = userDetails.getUsername();
		} else {
			errors.reject("ticketsPurchase.validation.user.notAuthenticated");
		}

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

		List<Ticket> competitionTickets = ticketService.getCompetitionTicketsByUserEmail(formData.getCompetitionId(), userEmail);
		if (newTicketsCount > 20 || competitionTickets.size() + newTicketsCount > 20) {
			errors.rejectValue("ticketsCount", "ticketsPurchase.validation.competition.limit");
		}

		List<Ticket> allUserTickets = ticketService.getTicketsByUserEmail(userEmail);
		if (newTicketsCount + allUserTickets.size() > 100) {
			errors.rejectValue("ticketsCount", "ticketsPurchase.validation.competitions.limit");
		}
	}

}
