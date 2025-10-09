package com.example.domain.usecases;

import com.example.domain.models.Recipe;
import com.example.domain.repository.RecipeRepository;

public class GetRecipeByIdUseCase {
    private RecipeRepository recipeRepository;

    public GetRecipeByIdUseCase(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe execute(int id) {
        return recipeRepository.getRecipeById(id);
    }
}
