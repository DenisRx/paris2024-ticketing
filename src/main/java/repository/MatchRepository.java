package repository;

import org.springframework.data.repository.CrudRepository;

import domain.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

}
