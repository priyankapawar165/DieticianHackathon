package com.ninja.dietician.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.ninja.dietician.entity.Recipe;
import com.ninja.dietician.service.RecipeService;

@RestController
@RequestMapping("/api")
public class RecipeController {

	@Autowired
	RecipeService recipeService;
	
	
    @GetMapping("/Recipes")
    public ResponseEntity<Iterable<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

	@GetMapping("/Recipes/FoodCategory/{recipeFoodCategory}")
	public ResponseEntity<List<Recipe>> getRecipesByFoodCategory (@PathVariable String recipeFoodCategory) {
	   System.out.println("In Controller class");
	    	return ResponseEntity.ok(recipeService.getRecipesByFoodCategory(recipeFoodCategory));
	 }
	
    @GetMapping("/Recipes/RecipeType/{recipeType}")
    public ResponseEntity<List<Recipe>> getRecipesByRecipeType (@PathVariable String recipeType) {
        return ResponseEntity.ok(recipeService.getRecipesByRecipeType(recipeType));
    }
    

    @GetMapping("/Recipes/RecipeIngredient/{Ingredient}")
    public ResponseEntity<Object> getRecipeByIngredient(@PathVariable("Ingredient") String ingredientName) {
      return ResponseEntity.ok(recipeService.getRecipeByIngredient(ingredientName));
    }
    

    @GetMapping("/Recipes/RecipeNutrient/{Nutrient}")
    public ResponseEntity<Object> getRecipeByNutrient( @PathVariable("Nutrient") String nutrientName) {
      return ResponseEntity.ok(recipeService.getRecipeByNutrient(nutrientName));
    }
}
