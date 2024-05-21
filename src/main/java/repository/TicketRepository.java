package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import domain.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

	@Query("SELECT t FROM Ticket t JOIN t.user u WHERE u.id = :userId")
	List<Ticket> findAllByUserId(@Param("userId") long userId);

	@Query("SELECT t FROM Ticket t JOIN t.competition c JOIN t.user u WHERE c.id = :competitionId AND u.id = :userId")
	List<Ticket> findAllByCompetitionIdAndUserId(@Param("competitionId") long competitionId,
			@Param("userId") long userId);

}
