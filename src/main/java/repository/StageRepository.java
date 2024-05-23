package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import domain.Stage;

public interface StageRepository extends CrudRepository<Stage, Long> {

	List<Stage> findAllByOrderByNameAsc();

}
