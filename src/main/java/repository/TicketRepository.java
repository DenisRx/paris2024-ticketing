package repository;

import org.springframework.data.repository.CrudRepository;

import domain.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
