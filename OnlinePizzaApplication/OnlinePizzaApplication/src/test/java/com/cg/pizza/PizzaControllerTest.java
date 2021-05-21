package com.cg.pizza;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;




@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PizzaControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void getPizzaTest_thenStatus200() throws Exception 
	{
		mvc.perform(get("http://localhost:9969/pizzahome/pizza").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].pizzaId", is(1)));
	}
	
	
	@Test
	public void getPizza_whennoCourse_thenStatus400() throws Exception {
		mvc.perform(get("http://localhost:9969/pizzahome/pizza")).andExpect(status().isNotFound());

	}
	
	@Test
	
	public void test_get_by_id_fail_404_not_found() throws Exception {
		mvc.perform(get("/pizzahome/{id}", 9891)).andExpect(status().isNotFound());
	}
}
