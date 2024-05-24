package service;

import java.util.List;
import java.util.Optional;

import domain.Competition;
import domain.CompetitionCreationFormData;
import domain.CompetitionPlaces;

public interface CompetitionService {
	List<Competition> getCompetitions();

	Optional<Competition> getCompetitionById(long id);

	List<Competition> getCompetitionsBySportId(long sportId);

	Competition createCompetition(long sportId, CompetitionCreationFormData formData);

	boolean olympicNumber1Exists(int olympicNumber);

	CompetitionPlaces getAvailablePlaces(long competitionId);
}
