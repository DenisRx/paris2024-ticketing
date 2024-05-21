package service;

import java.util.List;

import com.paris2024.ticketing.CompetitionCreationFormData;

import domain.Competition;

public interface CompetitionService {
	List<Competition> getCompetitions();

	Competition createCompetition(long sportId, CompetitionCreationFormData formData);
}
