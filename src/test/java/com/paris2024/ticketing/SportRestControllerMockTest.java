package com.paris2024.ticketing;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import domain.Competition;
import domain.Sport;
import domain.Stage;
import repository.SportRepository;
import service.CompetitionService;

@SpringBootTest
public class SportRestControllerMockTest {
	
	@Mock
	private CompetitionService competitionService;
	
	@Mock
	private SportRepository sportRepository;
	
	private SportRestController controller;
	private MockMvc mockMvc;
	
	private final long ID = 1;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		controller = new SportRestController();
		mockMvc = standaloneSetup(controller).build();
		ReflectionTestUtils.setField(controller, "competitionService", competitionService);
	}
	
	@Test
	public void testGetCompetitionsOfSport_emptyList() throws Exception {
		List<Competition> competitions = List.of();
		when(competitionService.getCompetitionsBySportId(ID)).thenReturn(competitions);
		
		mockMvc.perform(get("/sports/" + ID + "/competitions"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$").isEmpty());
	}
	
	@Test
	public void testGetCompetitionsOfSport() throws Exception {
		List<Competition> competitions = getCompetitions();
		Competition competition = competitions.getFirst();
		when(competitionService.getCompetitionsBySportId(ID)).thenReturn(competitions);
		
		mockMvc.perform(get("/sports/" + ID + "/competitions"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$").isNotEmpty())
			.andExpect(jsonPath("$[0].sport.name").value(competition.getSport().getName()))
			.andExpect(jsonPath("$[0].startDateTime").value(competition.getStartDateTime()))
			.andExpect(jsonPath("$[0].stage.name").value(competition.getStage().getName()))
			.andExpect(jsonPath("$[0].olympicNumber1").value(competition.getOlympicNumber1()))
			.andExpect(jsonPath("$[0].olympicNumber2").value(competition.getOlympicNumber2()))
			.andExpect(jsonPath("$[0].ticketPrice").value(competition.getTicketPrice()))
			.andExpect(jsonPath("$[0].seatsNumber").value(competition.getSeatsNumber()))
			.andExpect(jsonPath("$[0].remainingSeats").value(competition.getRemainingSeats()));
	}
	
	private List<Competition> getCompetitions() {
		Sport sport = new Sport("Tennis");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm").withZone(ZoneOffset.UTC);
		ZonedDateTime zonedDate1 = ZonedDateTime.parse("2024-07-26T12:00", formatter);
		Date date = Date.from(zonedDate1.toInstant());
		Stage stage = new Stage("Stade de France");
		return List.of(
				new Competition(sport, date, stage, 10002, 9002, new BigDecimal(50.00), 25)
			);
	}

}
