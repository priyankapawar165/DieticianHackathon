package com.ninja.dietician.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja.dietician.entity.Morbidity;
import com.ninja.dietician.service.MorbidityService;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(MorbidityController.class )
class MorbidityControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MorbidityService morbidityService;
	
	private List<Morbidity> morbidityList;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Morbidity morbidity1;
	
	@BeforeEach
	void setUp() throws Exception {
		String date = "07/13/2022";
		morbidity1 = new Morbidity("MR#Hypothyroidism", "TEST#HYPO_T3", "Morbidity", "Hypothyroidism",
				"HYPO_T3", "Triiodothyronine (T3)", "nanograms per decil", "between 60 and 180", date, date);
		Morbidity morbidity2 = new Morbidity("MR#Hypothyroidism", "TEST#THY_T4", "Morbidity", "Hypothyroidism",
				"THY_T4", "Triiodothyronine (T3)", "nanograms per decil", "between 60 and 180", date, date);
		morbidityList = new ArrayList<Morbidity> ();
		morbidityList.add(morbidity1);
		morbidityList.add(morbidity2);
	}

	@DisplayName("test for creating a new morbidity")
	@Test
	void testPostBody() throws Exception {
		//given
		given(morbidityService.addMorbidity(ArgumentMatchers.any(Morbidity.class)))
			.willReturn(morbidity1);
		
		//when
		ResultActions response = mockMvc.perform(post("/api/morbidity")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(morbidity1)));
		
		//then
		response.andExpect(status().isCreated());
	}
	
	
	@DisplayName("test to get all the morbidities")
	@Test
	void testGetAllMorbidities() throws Exception {
		//given
		given(morbidityService.getMorbidities()).willReturn(morbidityList);
		
		//when
		ResultActions response = mockMvc.perform(get("/api/morbidity"));
		
		//then
		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$", hasSize(morbidityList.size())));
	}

	@DisplayName("test to get morbidity by morbidityName")
	@Test
	void testGetMorbidityByName() throws Exception {
		//given 
		given(morbidityService.getMorbiditybyName("Hypothyroidism"))
			.willReturn(morbidityList);
		
		//when 
		ResultActions response = mockMvc.perform(get("/api/getMorbiditybyName/{morbidityName}", "Hypothyroidism"));
		
		//then
		response.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$", hasSize(morbidityList.size())));
		
	}
	
	@DisplayName("test to get morbidity by morbidityTestId")
	@Test
	void testGetMorbiditybyMorbidityTestId() throws Exception {
		//given 
		given(morbidityService.getMorbiditybyMorbidityTestId("HYPO_T3"))
			.willReturn(morbidityList);
		
		//when 
		ResultActions response = mockMvc.perform(get("/api/getMorbiditybyMorbidityTestId/{morbidityTestId}", "HYPO_T3"));
		
		//then
		response.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$", hasSize(morbidityList.size())));
		
	}
	
	@DisplayName("test for updating a morbidity")
	@Test
	void testUpdateMorbidities() throws Exception {
		//given
		Morbidity updateMorbidity = morbidity1;
		updateMorbidity.setMorbidityTestMarkerRef("100 – 125");
		given(morbidityService.updateMorbidities(ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class),
			ArgumentMatchers.any(Morbidity.class))).willReturn(updateMorbidity); 
		
		//when
		ResultActions response = mockMvc.perform(put("/api/morbidity/{morbidityName}/{morbidityTestId}", "Hypothyroidism", "HYPO_T3")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(updateMorbidity)));
		
		//then
		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.morbidityTestMarkerRef", is(updateMorbidity.getMorbidityTestMarkerRef())));
	}
	
	@DisplayName("test for deleting an morbidity")
	@Test
	void testdeleteMorbidities() throws Exception {
		//given
		given(morbidityService.deleteMorbidities("Hypothyroidism", "HYPO_T3"))
			.willReturn("A record is deleted successfully");  
		
		//when 
		ResultActions response = mockMvc.perform(delete("/api/morbidity/{morbidityName}/{morbidityTestId}",
			"Hypothyroidism", "HYPO_T3"));
			
		//then
		response.andExpect(status().isOk())
		.andDo(print())
		.andExpect(content().string("A record is deleted successfully"));
	}


}
