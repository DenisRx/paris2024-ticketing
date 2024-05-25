package com.paris2024.ticketing;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import domain.CompetitionPlaces;
import repository.SportRepository;
import service.CompetitionService;

@SpringBootTest
public class CompetitionRestControllerMockTest {
	
	@Mock
	private CompetitionService competitionService;
	
	@Mock
	private SportRepository sportRepository;
	
	private CompetitionRestController controller;
	private MockMvc mockMvc;
	
	private final long ID = 1;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		controller = new CompetitionRestController();
		mockMvc = standaloneSetup(controller).build();
		ReflectionTestUtils.setField(controller, "competitionService", competitionService);
	}
	
	@Test
	public void testGetAvailablePlaces_noPlacesLeft() throws Exception {
		CompetitionPlaces places = new CompetitionPlaces(ID, 0);
		when(competitionService.getAvailablePlaces(ID)).thenReturn(places);
		
		mockMvc.perform(get("/competitions/" + ID + "/availablePlaces"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isNotEmpty())
			.andExpect(jsonPath("$.competitionId").value(ID))
			.andExpect(jsonPath("$.availablePlaces").value(places.getAvailablePlaces()));
	}
	
	@Test
	public void testGetAvailablePlaces_placesLeft() throws Exception {
		CompetitionPlaces places = new CompetitionPlaces(ID, 25);
		when(competitionService.getAvailablePlaces(ID)).thenReturn(places);
		
		mockMvc.perform(get("/competitions/" + ID + "/availablePlaces"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isNotEmpty())
			.andExpect(jsonPath("$.competitionId").value(ID))
			.andExpect(jsonPath("$.availablePlaces").value(places.getAvailablePlaces()));
	}

}
