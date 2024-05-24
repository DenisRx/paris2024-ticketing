package service;

import domain.CompetitionPlaces;

public interface CompetitionRestService {

	CompetitionPlaces getCompetitionAvailablePlaces(long competitionId);

}
