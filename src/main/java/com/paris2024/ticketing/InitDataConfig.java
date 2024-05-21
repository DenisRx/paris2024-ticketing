package com.paris2024.ticketing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import domain.Competition;
import domain.Discipline;
import domain.Role;
import domain.Sport;
import domain.Stage;
import domain.Ticket;
import domain.User;
import repository.CompetitionRepository;
import repository.DisciplineRepository;
import repository.SportRepository;
import repository.StageRepository;
import repository.TicketRepository;
import repository.UserRepository;

@Component
public class InitDataConfig implements CommandLineRunner {

	@Autowired
	private DisciplineRepository disciplineRepository;

	@Autowired
	private CompetitionRepository competitionRepository;

	@Autowired
	private SportRepository sportRepository;

	@Autowired
	private StageRepository stageRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		List<String> sportNames = List.of("Archery", "Artistic gymnastics", "Artistic swimming", "Athletics",
				"Badminton", "Basketball", "Basketball 3x3", "Beach volleyball", "Boxing", "Breaking", "Canoe slalom",
				"Canoe sprint", "Cycling BMX freestyle", "Cycling BMX racing", "Cycling mountain bike", "Cycling road",
				"Cycling track", "Diving", "Equestrian", "Fencing", "Football", "Golf", "Handball", "Hockey", "Judo",
				"Marathon swimming", "Modern pentathlon", "Rhythmic gymnastics", "Rowing", "Rugby sevens", "Sailing",
				"Shooting", "Skateboarding", "Sport climbing", "Surfing", "Swimming", "Table tennis", "Taekwondo",
				"Tennis", "Trampoline", "Triathlon", "Volleyball", "Water polo", "Weightlifting", "Wrestling");
		List<Sport> sports = new ArrayList<>();
		sportNames.forEach(s -> sports.add(new Sport(s)));
		sportRepository.saveAll(sports);

		List<String> disciplineNames = List.of("Men's Individual", "Women's Individual", "Men's Team", "Women's Team",
				"Mixed Team");
		List<Discipline> disciplines = new ArrayList<>();
		disciplineNames.forEach(s -> disciplines.add(new Discipline(s, sports.get(0))));
		disciplineRepository.saveAll(disciplines);

		List<String> stageNames = List.of("Invalides", "Stade de France");
		List<Stage> stages = new ArrayList<>();
		stageNames.forEach(s -> stages.add(new Stage(s)));
		stageRepository.saveAll(stages);

		Calendar date1 = Calendar.getInstance();
		date1.set(Calendar.YEAR, 2024);
		date1.set(Calendar.MONTH, 7);
		date1.set(Calendar.DAY_OF_MONTH, 25);
		date1.set(Calendar.HOUR, 14);
		Calendar date2 = (Calendar) date1.clone();
		date2.set(Calendar.DAY_OF_MONTH, 26);
		date2.set(Calendar.HOUR, 16);
		List<Competition> competitions = List.of(
				new Competition(sports.get(0), date1.getTime(), stages.get(0), 12345, 22345, new BigDecimal(75.00), 500,
						new HashSet<Discipline>(List.of(disciplines.get(0)))),
				new Competition(sports.get(0), date2.getTime(), stages.get(0), 12346, 22346, new BigDecimal(80.00), 500,
						new HashSet<Discipline>(List.of(disciplines.get(1)))));
		competitionRepository.saveAll(competitions);

		List<User> users = List.of(new User("admin@student.hogent.be", "password", "Admin", "ADMIN", Role.ADMIN),
				new User("user1@student.hogent.be", "password", "User1", "USER", Role.USER),
				new User("user2@student.hogent.be", "password", "User2", "USER", Role.USER));
		userRepository.saveAll(users);

		List<Ticket> tickets = List.of(new Ticket(users.get(1), competitions.get(0)),
				new Ticket(users.get(2), competitions.get(0)), new Ticket(users.get(2), competitions.get(1)));
		ticketRepository.saveAll(tickets);
	}
}
