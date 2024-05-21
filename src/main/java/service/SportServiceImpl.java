package service;

import java.util.ArrayList;
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
		List<Sport> sports = new ArrayList<>();
		sportRepository.findAll().forEach(sports::add);

		return sports;
	}

	@Override
	public Optional<Sport> getSportById(long id) {
		return sportRepository.findById(id);
	}
}
