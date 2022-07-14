package com.ninja.dietician.repositories;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ninja.dietician.entity.Recipe;

@Repository
public class RecipeRepository {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	
	public List<Recipe> findAll(DynamoDBScanExpression scanExpression) {
		
		return dynamoDBMapper.scan(Recipe.class, scanExpression);
	}

	
	public List<Recipe> findByFoodCategory(DynamoDBScanExpression scanExpression) {
		List<Recipe> recipeListByFoodCategory = dynamoDBMapper.scan(Recipe.class, scanExpression);
		return recipeListByFoodCategory;
		
	}
	
	public List<Recipe> findByRecipeType(DynamoDBScanExpression scanExpression) {
		List<Recipe> recipeListByRecipeType = dynamoDBMapper.scan(Recipe.class, scanExpression);
		return recipeListByRecipeType;
	}
	
	  public List<Recipe> getRecipeByNutrient(DynamoDBScanExpression scanExpression) {
	      List<Recipe> recipeList = dynamoDBMapper.scan(Recipe.class, scanExpression);
	      return recipeList;
	     }
	
	  public List<Recipe> getRecipeByIngredient(DynamoDBScanExpression scanExpression) {
	      List<Recipe> recipeList = dynamoDBMapper.scan(Recipe.class, scanExpression);
	      return recipeList;
	     }

	  
	  
	  
	
	

	

}
