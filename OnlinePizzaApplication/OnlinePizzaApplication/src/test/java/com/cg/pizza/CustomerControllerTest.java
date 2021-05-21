package com.cg.pizza;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class CustomerControllerTest {
	@Autowired
	private MockMvc mvc;
	@Test
	public void getPizzaOrderTest_thenStatus200() throws Exception {
		mvc.perform(get("http://localhost:9999/customer").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].customerId", is(7)));
	}
	@Test
	public void givenCourses_whennoCourse_thenStatus400() throws Exception {
		mvc.perform(get("http://localhost:9999/pizzahome/pizza")).andExpect(status().isNotFound());

	}
	@Test
	
	public void test_get_all_success() throws Exception {
		mvc.perform(get("/courses")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(6)))
				.andExpect(jsonPath("$[0].customerId", is(7)))
				.andExpect(jsonPath("$[0].customerName", is("ABC")))
				.andExpect(jsonPath("$[1].customerId", is(5)))
				.andExpect(jsonPath("$[1].customerName", is("abc")));
	}
	@Test
	
	public void test_get_by_id_fail_404_not_found() throws Exception {
		mvc.perform(get("/customer/{id}",53 )).andExpect(status().isNotFound());
	}

}
