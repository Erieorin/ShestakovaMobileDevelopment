package com.example.domain.usecases;

import com.example.domain.models.Recipe;
import com.example.domain.repository.RecipeRepository;

public class AddRecipeUseCase {
    private RecipeRepository recipeRepository;

    public AddRecipeUseCase(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public boolean execute(Recipe recipe)
    {
       return recipeRepository.addRecipe(recipe);
    }
}