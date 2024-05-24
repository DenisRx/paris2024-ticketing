package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import domain.CompetitionPlaces;

public class CompetitionRestServiceImpl implements CompetitionRestService {

	@Autowired
	private WebClient webClient;

	public CompetitionPlaces getCompetitionAvailablePlaces(long competitionId) {
		return webClient.get().uri("/competitions/" + competitionId + "/availablePlaces").retrieve()
				.bodyToMono(CompetitionPlaces.class).block();
	}
}
