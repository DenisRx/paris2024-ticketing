package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import domain.Competition;

public interface CompetitionRepository extends CrudRepository<Competition, Long> {

	@Query("SELECT c FROM Competition c JOIN c.sport s WHERE s.id = :sportId")
	List<Competition> findAllBySportId(@Param("sportId") Long sportId);
}
