package com.paris2024.ticketing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import domain.Sport;
import repository.SportRepository;

@Component
public class InitDataConfig implements CommandLineRunner {

	@Autowired
	private SportRepository repository;

	@Override
	public void run(String... args) throws Exception {

		List<String> sportNames = List.of("Archery", "Artistic gymnastics", "Artistic swimming", "Athletics",
				"Badminton", "Basketball", "Basketball 3x3", "Beach volleyball", "Boxing", "Breaking", "Canoe slalom",
				"Canoe sprint", "Cycling BMX freestyle", "Cycling BMX racing", "Cycling mountain bike", "Cycling road",
				"Cycling track", "Diving", "Equestrian", "Fencing", "Football", "Golf", "Handball", "Hockey", "Judo",
				"Marathon swimming", "Modern pentathlon", "Rhythmic gymnastics", "Rowing", "Rugby sevens", "Sailing",
				"Shooting", "Skateboarding", "Sport climbing", "Surfing", "Swimming", "Table tennis", "Taekwondo",
				"Tennis", "Trampoline", "Triathlon", "Volleyball", "Water polo", "Weightlifting", "Wrestling");

		sportNames.forEach(s -> repository.save(new Sport(s)));
	}
}
