package com.example.rumireashestakovaculinarybook.domain.usecases;

import com.example.rumireashestakovaculinarybook.domain.models.Recipe;
import com.example.rumireashestakovaculinarybook.domain.repository.RecipeRepository;

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
