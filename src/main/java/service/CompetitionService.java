package service;

import java.util.List;
import java.util.Optional;

import com.paris2024.ticketing.CompetitionCreationFormData;

import domain.Competition;

public interface CompetitionService {
	List<Competition> getCompetitions();

	Optional<Competition> getCompetitionById(long id);

	List<Competition> getCompetitionsBySportId(long sportId);

	Competition createCompetition(long sportId, CompetitionCreationFormData formData);
}
