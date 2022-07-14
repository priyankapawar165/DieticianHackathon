package com.ninja.dietician.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.ninja.dietician.entity.Recipe;
import com.ninja.dietician.repositories.RecipeRepository;

@Service
public class RecipeService {

	@Autowired
	RecipeRepository recipeRepository;

	public Iterable<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}

	public List<Recipe> getRecipesByFoodCategory(String recipeFoodCategory) {
		return recipeRepository.findByFoodCategory(recipeFoodCategory);
	}

	public List<Recipe> getRecipesByRecipeType(String recipeType) {
		return recipeRepository.findByRecipeType(recipeType);
	}

	public List<Recipe> getRecipeByIngredient(String recipeIngredient) {
		return recipeRepository.getRecipeByIngredient(recipeIngredient);

	}

	public List<Recipe> getRecipeByNutrient(String recipeNutrient) {
		return recipeRepository.getRecipeByNutrient(recipeNutrient);
	}

}
