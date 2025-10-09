package com.example.domain.usecases;

import com.example.domain.models.Recipe;
import com.example.domain.repository.RecipeRepository;

public class AddRecipeToFavoritesUseCase {
    private RecipeRepository recipeRepository;

    public AddRecipeToFavoritesUseCase(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public boolean execute(Recipe recipe)
    {
        return recipeRepository.addRecipeToFavorites(recipe);
    }
}
