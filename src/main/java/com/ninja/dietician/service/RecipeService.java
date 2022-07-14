package com.ninja.dietician.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ninja.dietician.entity.Recipe;
import com.ninja.dietician.repositories.RecipeRepository;

@Service
public class RecipeService {

	@Autowired
	RecipeRepository recipeRepository;

	public Iterable<Recipe> getAllRecipes() {
		String infoType = "Recipe";		
		HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		eav1.put(":v1", new AttributeValue().withS(infoType));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				// Filter Expression
				.withFilterExpression("InfoType = :v1 ")
				.withExpressionAttributeValues(eav1);
		return recipeRepository.findAll(scanExpression);
	}

	public List<Recipe> getRecipesByFoodCategory(String recipeFoodCategory) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(recipeFoodCategory));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			.withFilterExpression("RecipeFoodCategory = :v1 ")
			.withExpressionAttributeValues(eav);
		return recipeRepository.findByFoodCategory(scanExpression);
	}

	public List<Recipe> getRecipesByRecipeType(String recipeType) {
		HashMap<String, AttributeValue> eav2 = new HashMap<String, AttributeValue>();
		eav2.put(":v1", new AttributeValue().withS(recipeType));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("RecipeType = :v1 ")
				.withExpressionAttributeValues(eav2);
		return recipeRepository.findByRecipeType(scanExpression);
	}

	public List<Recipe> getRecipeByIngredient(String recipeIngredient) {
		HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
	      eav1.put(":v1", new AttributeValue().withS(recipeIngredient));

	    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
	    		.withFilterExpression("contains(RecipeIngredient,:v1)")        
	    		.withExpressionAttributeValues(eav1);
		return recipeRepository.getRecipeByIngredient(scanExpression);

	}

	public List<Recipe> getRecipeByNutrient(String recipeNutrient) {
	      HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
	      eav1.put(":v1", new AttributeValue().withS(recipeNutrient));

	      DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
	    		  .withFilterExpression("contains(RecipeNutrient,:v1)")    
	    		  .withExpressionAttributeValues(eav1);

		return recipeRepository.getRecipeByNutrient(scanExpression);
	}

}
