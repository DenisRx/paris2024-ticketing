package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import domain.Stage;
import repository.StageRepository;

public class StageServiceImpl implements StageService {

	@Autowired
	private StageRepository stageRepository;

	@Override
	public List<Stage> getStages() {
		List<Stage> stages = new ArrayList<>();
		stageRepository.findAll().forEach(stages::add);

		return stages;
	}
}
