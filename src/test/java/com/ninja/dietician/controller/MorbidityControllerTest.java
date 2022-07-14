package com.ninja.dietician.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
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
	
	@BeforeEach
	void setUp() throws Exception {
		String date = "07/13/2022";
		Morbidity morbidity = new Morbidity("MR#Hypothyroidism", "TEST#HYPO_T3", "Morbidity", "Hypothyroidism",
				"HYPO_T3", "Triiodothyronine (T3)", "nanograms per decil", "between 60 and 180", date, date);
		Morbidity morbidity2 = new Morbidity();
		morbidity2.setPk("MR#Hypothyroidism");
		morbidity2.setSk("TEST#THY_T4");
		morbidityList = new ArrayList<Morbidity> ();
		morbidityList.add(morbidity);
		morbidityList.add(morbidity2);
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
	
	@DisplayName("test to get morbidity by test Id")
	@Test
	void testGetMorbidityByTestId() throws Exception {
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

}
