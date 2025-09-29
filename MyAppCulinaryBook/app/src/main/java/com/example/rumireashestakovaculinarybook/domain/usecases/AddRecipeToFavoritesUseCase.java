package com.example.rumireashestakovaculinarybook.domain.usecases;

import com.example.rumireashestakovaculinarybook.domain.models.Recipe;
import com.example.rumireashestakovaculinarybook.domain.repository.RecipeRepository;

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
