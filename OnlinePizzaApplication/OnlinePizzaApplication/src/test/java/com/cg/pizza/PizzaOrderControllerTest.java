package com.cg.pizza;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.pizza.entity.PizzaOrder;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class PizzaOrderControllerTest
{

	@Autowired
	private MockMvc mvc;
	
	@Test
	@Ignore
	public void getPizzaOrderTest_thenStatus200() throws Exception 
	{
		mvc.perform(get("http://localhost:9999/pizzaorder")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].bookingOrderId", is(1)));
	}
	@Test
	@Ignore
	public void getNoPizza_thenStatus400() throws Exception {
		mvc.perform(get("http://localhost:9999/pizza/pizzaid")).andExpect(status().is4xxClientError());

	}
	@Test
	@Ignore
	public void get_by_id_fail_404_not_found() throws Exception {
		mvc.perform(get("/pizzaorder/{pizzaBookOrderId}", 6)).andExpect(status().isNotFound());
	}
	
	@Test
	@Ignore
	public void test_get_all_success() throws Exception {
		mvc.perform(get("/pizzaorder")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(5)))
				.andExpect(jsonPath("$[0].bookingOrderId", is(1)))
				.andExpect(jsonPath("$[0].size", is("small")))
				.andExpect(jsonPath("$[3].bookingOrderId", is(14)))
				.andExpect(jsonPath("$[3].size", is("small")));
	}


	
	
}
