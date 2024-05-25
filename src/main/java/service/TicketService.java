package service;

import java.util.List;

import domain.Ticket;

public interface TicketService {
	List<Ticket> getTicketsByUserEmail(String userEmail);

	List<Ticket> getCompetitionTicketsByUserId(long competitionId, long userId);

	List<Ticket> purchaseTickets(long competitionId, long userId, int ticketsCount);
}
