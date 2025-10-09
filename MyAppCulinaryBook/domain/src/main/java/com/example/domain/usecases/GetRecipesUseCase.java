package com.example.domain.usecases;

import com.example.domain.models.Recipe;
import com.example.domain.repository.RecipeRepository;

import java.util.List;

public class GetRecipesUseCase {
    private final RecipeRepository repository;

    public GetRecipesUseCase(RecipeRepository repository) {
        this.repository = repository;
    }

    public List<Recipe> execute() {
        return repository.getRecipes();
    }
}