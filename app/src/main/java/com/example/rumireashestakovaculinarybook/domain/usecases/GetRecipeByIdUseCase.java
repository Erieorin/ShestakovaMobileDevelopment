package com.example.rumireashestakovaculinarybook.domain.usecases;

import com.example.rumireashestakovaculinarybook.domain.models.Recipe;
import com.example.rumireashestakovaculinarybook.domain.repository.RecipeRepository;

public class GetRecipeByIdUseCase {
    private RecipeRepository recipeRepository;

    public GetRecipeByIdUseCase(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe execute(int id) {
        return recipeRepository.getRecipeById(id);
    }
}
