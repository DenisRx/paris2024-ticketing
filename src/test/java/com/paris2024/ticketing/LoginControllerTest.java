package com.paris2024.ticketing;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void loginGet() throws Exception {
		mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("login"));
	}

	@Test
	public void loginParamErrorGet() throws Exception {
		mockMvc.perform(get("/login").param("error", ""))
			.andExpect(status().isOk())
			.andExpect(view().name("login"))
			.andExpect(model().attributeExists("error"))
			.andExpect(model().attribute("error", true));
	}

	@Test
	public void loginParamLogoutGet() throws Exception {
		mockMvc.perform(get("/login").param("logout", ""))
			.andExpect(status().isOk())
			.andExpect(view().name("login"))
			.andExpect(model().attributeExists("logout"))
			.andExpect(model().attribute("logout", true));
	}

}