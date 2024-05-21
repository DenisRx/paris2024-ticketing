package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import domain.Discipline;
import repository.DisciplineRepository;

public class DisciplineServiceImpl implements DisciplineService {

	@Autowired
	private DisciplineRepository disciplineRepository;

	@Override
	public List<Discipline> getDisciplines() {
		List<Discipline> disciplines = new ArrayList<>();
		disciplineRepository.findAll().forEach(disciplines::add);

		return disciplines;
	}
}
