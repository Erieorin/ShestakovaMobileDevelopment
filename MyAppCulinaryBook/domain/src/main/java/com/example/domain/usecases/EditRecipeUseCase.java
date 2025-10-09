package com.example.domain.usecases;

import com.example.domain.models.Recipe;
import com.example.domain.repository.RecipeRepository;

public class EditRecipeUseCase {
    private RecipeRepository recipeRepository;

    public EditRecipeUseCase(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public boolean execute(Recipe recipe) {
        return recipeRepository.editRecipe(recipe);
    }
}
