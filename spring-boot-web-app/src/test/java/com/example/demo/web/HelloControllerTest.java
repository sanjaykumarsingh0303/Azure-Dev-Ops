package com.example.demo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void helloReturnsDefaultGreeting() throws Exception {
		mockMvc.perform(get("/api/hello"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Hello, World!"))
				.andExpect(jsonPath("$.greeting").value("Hello from local"));
	}

	@Test
	void helloReturnsNamedGreeting() throws Exception {
		mockMvc.perform(get("/api/hello").param("name", "Sanjay"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Hello, Sanjay!"));
	}
}
