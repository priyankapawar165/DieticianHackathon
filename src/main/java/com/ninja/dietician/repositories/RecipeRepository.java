package com.ninja.dietician.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.s3.model.EmailAddressGrantee;
import com.ninja.dietician.entity.Recipe;

@Repository
public class RecipeRepository {

	@Autowired
	private DynamoDBMapper mapper;

	
	public List<Recipe> findAll() {
		String infoType = "Recipe";
		
		HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		eav1.put(":v1", new AttributeValue().withS(infoType));

		System.out.println("eav1" +eav1);
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				// Filter Expression
				.withFilterExpression("InfoType = :v1 ")
				.withExpressionAttributeValues(eav1);
		
		return mapper.scan(Recipe.class, scanExpression);
	
		
	}

	
	public List<Recipe> findByFoodCategory(String recipeFoodCategory) {
		
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(recipeFoodCategory));

		System.out.println("eav" +eav);
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				// Filter Expression
				.withFilterExpression("RecipeFoodCategory = :v1 ")
				.withExpressionAttributeValues(eav);
		
		List<Recipe> recipeListByFoodCategory = mapper.scan(Recipe.class, scanExpression);
		
		return recipeListByFoodCategory;
		
	}
	
	
	public List<Recipe> findByRecipeType(String recipeType) {
		
		HashMap<String, AttributeValue> eav2 = new HashMap<String, AttributeValue>();
		eav2.put(":v1", new AttributeValue().withS(recipeType));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				// Filter Expression
				.withFilterExpression("RecipeType = :v1 ")
				.withExpressionAttributeValues(eav2);

		List<Recipe> recipeListByRecipeType = mapper.scan(Recipe.class, scanExpression);
		
		return recipeListByRecipeType;
	}
	
	  public List<Recipe> getRecipeByNutrient(String recipeNutrient) {
	      HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
	      eav1.put(":v1", new AttributeValue().withS(recipeNutrient));

	      DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
	        // Filter Expression
	      .withFilterExpression("contains(RecipeNutrient,:v1)")    
	        .withExpressionAttributeValues(eav1);

	      List<Recipe> recipeList = mapper.scan(Recipe.class, scanExpression);
	      
	      return recipeList;
	     }
	
	  public List<Recipe> getRecipeByIngredient(String recipeIngredient) {
	      HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
	      eav1.put(":v1", new AttributeValue().withS(recipeIngredient));

	      DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
	      .withFilterExpression("contains(RecipeIngredient,:v1)")        
	        .withExpressionAttributeValues(eav1);

	      List<Recipe> recipeList = mapper.scan(Recipe.class, scanExpression);
	      
	      return recipeList;
	     }

	  
	  
	  
	  
	  
	  
	  
		
		
		/*DynamoDBScanExpression e = new DynamoDBScanExpression();
		e.addExpressionAttributeNamesEntry("Email", "barbie@gmail.com");
		e.addFilterCondition("Allergy", Condition.);
		dynamoDBMapper.query(null, null).
		return dynamoDBMapper.scan(Recipe.class, new DynamoDBScanExpression());*/
	  
	  
	  
		/*
		
		String forumName = "RECIPE";
		String forumSubject = recipeFoodCategory;
		String partitionKey = forumName + "#" + forumSubject;
		//Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
		//expressionAttributeValues.put(partitionKey, null)
		
		System.out.println("partitionKey :" + partitionKey);
		
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		
		eav.put(":v1", new AttributeValue().withS(partitionKey));
		eav.put(":v2",new AttributeValue().withS(recipeFoodCategory))	;
		System.out.println("eav :" + eav);
		
		DynamoDBQueryExpression<Recipe> queryExpression = new DynamoDBQueryExpression<Recipe>()
	            .withKeyConditionExpression("PK = :val1");
	            //.withExpressionAttributeValues(eav);
		
		List<Recipe> latestReplies = dynamoDBMapper.query(Recipe.class, queryExpression);
		return latestReplies;
		}
	*/
	
	

	

}
