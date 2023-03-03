package com.skiResort.server.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.skiResort.server.entity.SkiLiftRideEvent;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SkiResortController.class)
public class SkiResortControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void whenValidInput_thenReturns200() throws Exception {
		SkiLiftRideEvent event = new SkiLiftRideEvent();
		event.setDayID(1);
		event.setSkierID(1);
		event.setLiftID(1);
		event.setTime(1);
		event.setSeasonID(2022);
		event.setResortID(1);
		Gson gson = new Gson();

		mockMvc.perform(post("/skiers").content(gson.toJson(event))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void whenInValidInput_thenReturns400() throws Exception {
		SkiLiftRideEvent event = new SkiLiftRideEvent();
		event.setDayID(1);
		event.setSkierID(1);
		event.setLiftID(1);
		event.setTime(1);
		event.setSeasonID(2024);
		event.setResortID(1);
		Gson gson = new Gson();

		mockMvc.perform(post("/skiers").content(gson.toJson(event))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
}
