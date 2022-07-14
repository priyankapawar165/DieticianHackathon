package com.ninja.dietician.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.ninja.dietician.entity.Recipe;

@Repository
public class RecipeRepository {

	@Autowired
	private DynamoDBMapper mapper;

	
	public List<Recipe> findAll(DynamoDBScanExpression scanExpression) {
		
		return mapper.scan(Recipe.class, scanExpression);
	}

	
	public List<Recipe> findByFoodCategory(DynamoDBScanExpression scanExpression) {
		List<Recipe> recipeListByFoodCategory = mapper.scan(Recipe.class, scanExpression);
		return recipeListByFoodCategory;
		
	}
	
	public List<Recipe> findByRecipeType(DynamoDBScanExpression scanExpression) {
		List<Recipe> recipeListByRecipeType = mapper.scan(Recipe.class, scanExpression);
		return recipeListByRecipeType;
	}
	
	  public List<Recipe> getRecipeByNutrient(DynamoDBScanExpression scanExpression) {
	      List<Recipe> recipeList = mapper.scan(Recipe.class, scanExpression);
	      return recipeList;
	     }
	
	  public List<Recipe> getRecipeByIngredient(DynamoDBScanExpression scanExpression) {
	      List<Recipe> recipeList = mapper.scan(Recipe.class, scanExpression);
	      return recipeList;
	     }

	  
	  
}