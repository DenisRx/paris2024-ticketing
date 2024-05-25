package com.paris2024.ticketing;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@GetMapping
	public String showTickets(Model model, Principal principal) {
		model.addAttribute("tickets", ticketService.getTicketsByUserEmail(principal.getName()));

		return "ticketsList";
	}
}
