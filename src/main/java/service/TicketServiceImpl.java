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
	public List<Ticket> getTicketsByUserId(long userId) {
		return ticketRepository.findAllByUserId(userId);
	}

	@Override
	public List<Ticket> getCompetitionTicketsByUserId(long competitionId, long userId) {
		return ticketRepository.findAllByCompetitionIdAndUserId(competitionId, userId);
	}

	@Override
	public List<Ticket> purchaseTickets(long competitionId, long userId, int ticketsCount) {
		Optional<Competition> competition = competitionRepository.findById(competitionId);
		Optional<User> user = userRepository.findById(userId);

		if (!competition.isPresent() || !user.isPresent()) {
			return null;
		}

		List<Ticket> tickets = new ArrayList<>();
		for (int i = 0; i < ticketsCount; i++) {
			tickets.add(new Ticket(user.get(), competition.get()));
		}

		return (List<Ticket>) ticketRepository.saveAll(tickets);
	}
}
