package service;

import java.util.List;

import domain.Competition;

public interface SportRestService {

	List<Competition> getSportCompetitionsOrderByDateTime(long sportId);

}
