package service;

import java.util.List;
import java.util.Optional;

import domain.Sport;

public interface SportService {
	List<Sport> getSports();

	Optional<Sport> getSportById(long id);
}
