package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import domain.Ticket;
import repository.TicketRepository;

public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public List<Ticket> getTicketsByUserId(long userId) {
		return ticketRepository.findAllByUserId(userId);
	}
}
