package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import domain.Competition;
import domain.Ticket;
import domain.User;
import repository.CompetitionRepository;
import repository.TicketRepository;
import repository.UserRepository;

public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private CompetitionRepository competitionRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Ticket> getTicketsByUserEmail(String userEmail) {
		return ticketRepository.findAllByUserEmail(userEmail);
	}

	@Override
	public List<Ticket> getCompetitionTicketsByUserEmail(long competitionId, String userEmail) {
		return ticketRepository.findAllByCompetitionIdAndUserEmail(competitionId, userEmail);
	}

	@Override
	public List<Ticket> purchaseTickets(long competitionId, String userEmail, int ticketsCount) {
		Optional<Competition> competition = competitionRepository.findById(competitionId);
		User user = userRepository.findByEmail(userEmail);

		if (competition.isEmpty() || user == null) {
			return null;
		}

		List<Ticket> tickets = new ArrayList<>();
		for (int i = 0; i < ticketsCount; i++) {
			tickets.add(new Ticket(user, competition.get()));
		}

		Competition competitionValue = competition.get();
		int newRemainingSeatsCount = competitionValue.getRemainingSeats() - ticketsCount;
		if (newRemainingSeatsCount < 0) {
			return null;
		}

		competitionValue.setRemainingSeats(newRemainingSeatsCount);
		if (competitionRepository.save(competitionValue) == null) {
			return null;
		}

		return (List<Ticket>) ticketRepository.saveAll(tickets);
	}
}
