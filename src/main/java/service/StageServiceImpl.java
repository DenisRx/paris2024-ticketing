package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import domain.Stage;
import repository.StageRepository;

public class StageServiceImpl implements StageService {

	@Autowired
	private StageRepository stageRepository;

	@Override
	public List<Stage> getStages() {
		return (List<Stage>) stageRepository.findAll();
	}
}
