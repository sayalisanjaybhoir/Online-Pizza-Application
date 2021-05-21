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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.pizza.entity.Coupan;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CoupanControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@Test
	
	public void getCoupanTest_thenStatus200() throws Exception 
	{
		mvc.perform(get("http://localhost:8876/coupan/coupan")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[2].coupanId", is(6)))
		.andExpect(jsonPath("$[2].coupanName", is("raza")));
	}
	
	@Test
	@Ignore
	public void givenCoupan_thenStatus400() throws Exception {
		mvc.perform(get("http://localhost:8876/coupan/coupanhaj")).andExpect(status().is4xxClientError());

	}
	
	@Test
	@Ignore
	public void test_get_all_success() throws Exception {
		mvc.perform(get("/coupan/coupan")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(14)))
				.andExpect(jsonPath("$[1].coupanId", is(6)))
				.andExpect(jsonPath("$[1].coupanName", is("raza")))
				.andExpect(jsonPath("$[4].coupanId", is(9)))
				.andExpect(jsonPath("$[4].coupanName", is("barney")));
	}
	

	@Test
	@Ignore
	public void getallcoupans_notfound() throws Exception{
		mvc.perform(get("http://localhost:8876/coupan/{coupanId}",11))
		.andExpect(status().isNotFound());
		
	}
	
	@Test
	@Ignore
	public void getallcoupans_found() throws Exception{
		mvc.perform(get("http://localhost:8876/coupan/{coupanId}",6)
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("coupanId", is(6)))
		.andExpect(jsonPath("coupanName", is("raza")));
		
	}
	
	@Test
	@Ignore
	public void deletecoupans_found() throws Exception{
		mvc.perform(delete("http://localhost:8876/coupan/{coupanId}",7)
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
	@Test
	@Ignore
	public void deletecoupans_notfound() throws Exception{
		mvc.perform(get("http://localhost:8876/coupan/{coupanId}",11))
		.andExpect(status().isNotFound());
		
	}
	
	
    @Test
	@Ignore
	public void test_insertcoupan_success() throws Exception {
    Coupan coupan=new Coupan(500,"pizza","Chicken",90);
		
    mvc.perform(post("http://localhost:8876/coupan").contentType(MediaType.APPLICATION_JSON).content(asJsonString(coupan)))
    .andExpect(status().isCreated())
    .andExpect(header().string("location", containsString("http://localhost:8876/coupan/500")));
	}

public static String asJsonString(final Object obj) {
    try {
        return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

@Test
@Ignore
public void test_editcoupan_success() throws Exception {
Coupan coupan=new Coupan(201,"pizza","fish",90);
	
mvc.perform(put("http://localhost:8876/coupan/{coupanId}",201).contentType(MediaType.APPLICATION_JSON).content(asJsonString(coupan)))
.andExpect(status().isCreated())
.andExpect(header().string("location", containsString("http://localhost:8876/coupan/201")));
}


}
