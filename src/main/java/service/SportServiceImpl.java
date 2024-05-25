package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import domain.Sport;
import exception.SportNotFoundException;
import repository.SportRepository;

public class SportServiceImpl implements SportService {

	@Autowired
	private SportRepository sportRepository;

	@Override
	public List<Sport> getSports() {
		return (List<Sport>) sportRepository.findAll();
	}

	@Override
	public Sport getSportById(long sportId) {
		
		Optional<Sport> sport = sportRepository.findById(sportId);
		if (sport.isEmpty()) {
			throw new SportNotFoundException(sportId);
		}
		
		return sport.get();
	}
}
