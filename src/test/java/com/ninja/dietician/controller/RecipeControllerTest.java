package com.ninja.dietician.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ninja.dietician.entity.Recipe;
import com.ninja.dietician.service.RecipeService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {
  
	 @Autowired
	  private MockMvc mockMvc;
	  
	  @MockBean
	  private RecipeService recipeService;
	  
	  private List<Recipe> recipeList;
	  
	  
	  @DisplayName("test to get all recipes")
	  @Test
	  void testGetAllRecipes() throws Exception{
		    //given 
		    given(recipeService.getAllRecipes())
		      .willReturn(recipeList);
		    
		    //when 
		    ResultActions response = mockMvc.perform(get("/api/Recipes"));
		    
		    //then
		    response.andExpect(status().isOk()); 
	  }

	  @DisplayName("test to get recipes by Food Category")
	  @Test
	  void testGetRecipesByFoodCategory() throws Exception{
		    //given 
		    given(recipeService.getRecipesByFoodCategory("Vegetarian"))
		      .willReturn(recipeList);
		    
		    //when 
		    ResultActions response = mockMvc.perform(get("/api/Recipes/FoodCategory/{recipeFoodCategory}", "Vegetarian"));
		    
		    //then
		    response.andExpect(status().isOk()); 
	  }
	  
	  @DisplayName("test to get recipes by RecipeType")
	  @Test
	  void testGetRecipesByRecipeType() throws Exception{
		    //given 
		    given(recipeService.getRecipesByRecipeType("Lunch"))
		      .willReturn(recipeList);
		    
		    //when 
		    ResultActions response = mockMvc.perform(get("/api/Recipes/RecipeType/{recipeType}", "Lunch"));
		    
		    //then
		    response.andExpect(status().isOk()); 
	  }
	  
	  @DisplayName("test to get recipes by recipeIngredient")
	  @Test
	  void testGetMorbidityByName() throws Exception {
	    //given 
	    given(recipeService.getRecipeByIngredient("Ajwain"))
	      .willReturn(recipeList);
	    
	    //when 
	    ResultActions response = mockMvc.perform(get("/api/Recipes/RecipeIngredient/{Ingredient}", "Ajwain"));
	    
	    //then
	    response.andExpect(status().isOk());  
	  }
	  
	  @DisplayName("test to get recipes by recipeNutrient")
	  @Test
	  void testGetRecipeByNutrient() throws Exception {
	    //given 
	    given(recipeService.getRecipeByNutrient("Sodium"))
	      .willReturn(recipeList);
	    
	    //when 
	    ResultActions response = mockMvc.perform(get("/api/Recipes/RecipeNutrient/{Nutrient}", "Sodium"));
	    
	    //then
	    response.andExpect(status().isOk());    
	  }

}