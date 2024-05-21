package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import domain.Sport;
import repository.SportRepository;

public class SportServiceImpl implements SportService {

	@Autowired
	private SportRepository sportRepository;

	@Override
	public List<Sport> getSports() {
		return (List<Sport>) sportRepository.findAll();
	}

	@Override
	public Optional<Sport> getSportById(long id) {
		return sportRepository.findById(id);
	}
}
