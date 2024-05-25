package com.paris2024.ticketing;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import domain.Competition;
import domain.Role;
import domain.Sport;
import domain.Stage;
import domain.Ticket;
import repository.TicketRepository;
import repository.UserRepository;
import service.TicketService;

@Import(SecurityConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerMockTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TicketService ticketService;

	@MockBean
	private UserDetailsService userService;

	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private TicketRepository ticketRepository;
	
	@BeforeEach
	public void setup() {
		domain.User basicUser = new domain.User("user@student.hogent.be", "password", "userFName", "userLName",
				Role.USER);
		domain.User adminUser = new domain.User("admin@student.hogent.be", "password", "adminFName", "adminLName",
				Role.ADMIN);

		User user = new User(basicUser.getEmail(), basicUser.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
		User admin = new User(adminUser.getEmail(), adminUser.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));

		when(userService.loadUserByUsername("user@student.hogent.be")).thenReturn(user);
		when(userService.loadUserByUsername("admin@student.hogent.be")).thenReturn(admin);
		when(userRepository.findByEmail("user@student.hogent.be")).thenReturn(basicUser);
		when(userRepository.findByEmail("admin@student.hogent.be")).thenReturn(adminUser);
	}

	@WithAnonymousUser
	@Test
	public void accessDeniedAnonymous() throws Exception {
		mockMvc.perform(get("/tickets"))
			.andExpect(redirectedUrlPattern("**/login"));
	}
    
    @Test
    @WithMockUser(username = "admin@student.hogent.be", roles = { "ADMIN" })
    public void accessDeniedForAdmin() throws Exception {
    	
        mockMvc.perform(get("/tickets"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user@student.hogent.be", roles = { "USER" })
    public void showTicketsListOfUser() throws Exception {
    	List<Ticket> ticketsList = getTickets();
		when(ticketService.getTicketsByUserEmail("user@student.hogent.be")).thenReturn(ticketsList);
		when(ticketRepository.findAllByUserEmail("user@student.hogent.be")).thenReturn(ticketsList);
    	
    	mockMvc.perform(get("/tickets"))
        	.andExpect(status().isOk())
        	.andExpect(view().name("ticketsList"))
        	.andExpect(model().attributeExists("tickets"))
        	.andExpect(model().attribute("tickets", getTickets()));
    }
    
    @Test
    @WithMockUser(username = "user@student.hogent.be", roles = { "USER" })
    public void noTickets() throws Exception {
    	List<Ticket> ticketsList = List.of();
		when(ticketService.getTicketsByUserEmail("user@student.hogent.be")).thenReturn(ticketsList);
		when(ticketRepository.findAllByUserEmail("user@student.hogent.be")).thenReturn(ticketsList);
    	
    	mockMvc.perform(get("/tickets"))
        	.andExpect(status().isOk())
        	.andExpect(view().name("ticketsList"))
        	.andExpect(model().attributeExists("tickets"))
        	.andExpect(model().attribute("tickets", List.of()));
    }
    
    private List<Ticket> getTickets() {
    	domain.User user = new domain.User("user@student.hogent.be", "password", "userFName", "userLName", Role.USER);
		Sport sport = new Sport("Tennis");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm").withZone(ZoneOffset.UTC);
		ZonedDateTime zonedDate1 = ZonedDateTime.parse("2024-07-26T12:00", formatter);
		Date date = Date.from(zonedDate1.toInstant());
		Stage stage = new Stage("Stade de France");
		Competition competition = new Competition(sport, date, stage, 10000, 9000, new BigDecimal(50.00), 25);		
		
		return List.of(new Ticket(user, competition), new Ticket(user, competition));
    }

}
