package com.paris2024.ticketing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(SecurityConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
class Paris2024TicketingApplicationTests {

	@Test
	void contextLoads() {
	}

}
