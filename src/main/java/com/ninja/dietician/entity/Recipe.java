package com.ninja.dietician.entity;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@DynamoDBTable(tableName = "DietProject_Table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

	@JsonIgnore
	@DynamoDBHashKey (attributeName="PK")
	@DynamoDBAttribute(attributeName = "PK")
	private String pk;

	//@DynamoDBAttribute(attributeName = "SK")
	@JsonIgnore
	@DynamoDBRangeKey(attributeName="SK")
	private String sk;
	
	public String getSk() {
		return "RID#" + recipeId;
	}

	public void setSk(String sk) {
		this.sk = sk;
	}

	public String getPk() {
		return "RECIPE#" + recipeFoodCategory;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	@DynamoDBAttribute(attributeName = "RecipeId")
	private int recipeId;
	
	@DynamoDBAttribute(attributeName = "RecipeFoodCategory")
	private String recipeFoodCategory;
	
	@DynamoDBAttribute(attributeName = "RecipeType")
	private String recipeType;
	
	@DynamoDBAttribute(attributeName = "RecipeName")
	private String recipeName;
	
	@DynamoDBAttribute(attributeName = "RecipeIngredient")
	private String recipeIngredient;
	
	@DynamoDBAttribute(attributeName = "RecipeNutrient")
	private String recipeNutrient;
	
	@DynamoDBAttribute(attributeName = "RecipeUrl")
	private String recipeUrl;
	
	@DynamoDBAttribute(attributeName = "RecipeImg")
	private String recipeImg;
	
	@DynamoDBAttribute(attributeName = "RecipeStep")
	private String recipeStep;
	
	@JsonIgnore
	@DynamoDBAttribute(attributeName = "InfoType")
	private String infoType;
	

}