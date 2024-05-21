package service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.paris2024.ticketing.CompetitionCreationFormData;

import domain.Competition;
import domain.Discipline;
import domain.Sport;
import domain.Stage;
import repository.CompetitionRepository;
import repository.DisciplineRepository;
import repository.SportRepository;
import repository.StageRepository;

public class CompetitionServiceImpl implements CompetitionService {

	@Autowired
	CompetitionRepository competitionRepository;

	@Autowired
	SportRepository sportRepository;

	@Autowired
	StageRepository stageRepository;

	@Autowired
	DisciplineRepository disciplineRepository;

	@Override
	public List<Competition> getCompetitions() {
		return (List<Competition>) competitionRepository.findAll();
	}

	@Override
	public Optional<Competition> getCompetitionById(long id) {
		return competitionRepository.findById(id);
	}

	@Override
	public List<Competition> getCompetitionsBySportId(long sportId) {
		return competitionRepository.findAllBySportId(sportId);
	}

	@Override
	public Competition createCompetition(long sportId, CompetitionCreationFormData formData) {
		Optional<Sport> sport = sportRepository.findById(sportId);
		if (!sport.isPresent()) {
			return null;
		}

		Optional<Stage> stage = stageRepository.findById(formData.getStageId());
		if (!stage.isPresent()) {
			return null;
		}

		Set<Discipline> disciplines = new HashSet<>();
		Optional<Discipline> discipline1 = disciplineRepository.findById(formData.getDisciplineId1());
		Optional<Discipline> discipline2 = disciplineRepository.findById(formData.getDisciplineId2());
		if (discipline1.isPresent()) {
			disciplines.add(discipline1.get());
		}
		if (discipline2.isPresent()) {
			disciplines.add(discipline2.get());
		}

		Competition newCompetition = new Competition(sport.get(), formData.getStartDateTime(), stage.get(),
				formData.getOlympicNumber1(), formData.getOlympicNumber2(), formData.getTicketPrice(),
				formData.getSeatNumber(), disciplines);

		return competitionRepository.save(newCompetition);
	}

}
