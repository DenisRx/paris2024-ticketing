package service;

import java.util.List;

import domain.Ticket;

public interface TicketService {
	List<Ticket> getTicketsByUserEmail(String userEmail);

	List<Ticket> getCompetitionTicketsByUserEmail(long competitionId, String userEmail);

	List<Ticket> purchaseTickets(long competitionId, String userEmail, int ticketsCount);
}
