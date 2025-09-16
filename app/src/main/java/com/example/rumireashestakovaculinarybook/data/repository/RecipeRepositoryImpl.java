package com.example.rumireashestakovaculinarybook.data.repository;

import com.example.rumireashestakovaculinarybook.domain.models.Recipe;
import com.example.rumireashestakovaculinarybook.domain.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepositoryImpl implements RecipeRepository {
    private List<Recipe> testRecipes = new ArrayList<>();

    public RecipeRepositoryImpl() {
        testRecipes.add(new Recipe(1, "Борщ"));
        testRecipes.add(new Recipe(2, "Паста Карбонара"));
    }

    @Override
    public List<Recipe> getRecipes() {
        return testRecipes;
    }

    @Override
    public Recipe getRecipeById(int id) {
        for (Recipe recipe : testRecipes) {
            if (recipe.getId() == id) return recipe;
        }
        return null;
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        return testRecipes.add(recipe);
    }
}