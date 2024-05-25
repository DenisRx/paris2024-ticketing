package service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import domain.Competition;

public class SportRestServiceImpl implements SportRestService {

	@Autowired
	private WebClient webClient;

	@Override
	public List<Competition> getSportCompetitionsOrderByDateTime(long sportId) {
		List<Competition> competitions = webClient.get().uri("/sports/" + sportId + "/competitions").retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Competition>>() {
				}).block();

		Collections.sort(competitions, new Comparator<Competition>() {
			@Override
			public int compare(Competition c1, Competition c2) {
				return c1.getStartDateTime().compareTo(c2.getStartDateTime());
			}
		});

		return competitions;
	}

}
