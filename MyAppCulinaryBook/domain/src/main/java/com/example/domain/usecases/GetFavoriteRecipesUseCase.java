package com.example.domain.usecases;

import com.example.domain.models.Recipe;
import com.example.domain.repository.RecipeRepository;

import java.util.List;

public class GetFavoriteRecipesUseCase {
    private RecipeRepository recipeRepository;

    public GetFavoriteRecipesUseCase(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> execute() {
        return recipeRepository.getFavorites();
    }
}
