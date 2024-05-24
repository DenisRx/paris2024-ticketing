package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import domain.Competition;

public class SportRestServiceImpl implements SportRestService {

	@Autowired
	private WebClient webClient;

	@Override
	public List<Competition> getSportCompetitions(long sportId) {
		return webClient.get().uri("/sports/" + sportId + "/competitions").retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Competition>>() {
				}).block();
	}

}
