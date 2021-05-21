package com.cg.pizza;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.pizza.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllertest {
	@Autowired
	private MockMvc mvc;

	@Test

	public void test_insertcoupan_success() throws Exception {
		User user = new User(34, "satyam", "password");

		mvc.perform(post("http://localhost:9988/user/addNewUser").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user))).andExpect(status().isCreated())
				.andExpect(header().string("location", containsString("http://localhost:9988/user/addNewUser/34")));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
