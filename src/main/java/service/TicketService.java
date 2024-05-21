package service;

import java.util.List;

import domain.Ticket;

public interface TicketService {
	List<Ticket> getTicketsByUserId(long userId);
}
