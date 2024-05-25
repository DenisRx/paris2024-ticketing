package service;

import java.util.List;

import domain.Sport;

public interface SportService {
	List<Sport> getSports();

	Sport getSportById(long sportId);
}
