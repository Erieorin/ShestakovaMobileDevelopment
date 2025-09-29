package com.example.rumireashestakovaculinarybook.domain.usecases;

import com.example.rumireashestakovaculinarybook.domain.models.Recipe;
import com.example.rumireashestakovaculinarybook.domain.repository.RecipeRepository;

public class EditRecipeUseCase {
    private RecipeRepository recipeRepository;

    public EditRecipeUseCase(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public boolean execute(Recipe recipe) {
        return recipeRepository.editRecipe(recipe);
    }
}
