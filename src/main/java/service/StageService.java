package service;

import java.util.List;

import domain.Stage;

public interface StageService {
	List<Stage> getStages();

	List<Stage> getStagesOrderByName();
}
