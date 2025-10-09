package com.example.domain.usecases;

import com.example.domain.models.Recipe;
import com.example.domain.repository.RecipeRepository;

import java.util.List;

public class GetRecipesUseCase {
    private RecipeRepository recipeRepository;

    public GetRecipesUseCase(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> execute() {
        return recipeRepository.getRecipes();
    }
}