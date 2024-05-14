package repository;

import org.springframework.data.repository.CrudRepository;

import domain.Competition;

public interface CompetitionRepository extends CrudRepository<Competition, Long> {

}
