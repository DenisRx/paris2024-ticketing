package com.paris2024.ticketing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import repository.TicketRepository;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;

	@GetMapping
	public String showSports(Model model) {
		model.addAttribute("tickets", ticketRepository.findAllByUserId((long) 3));

		return "ticketsList";
	}
}
