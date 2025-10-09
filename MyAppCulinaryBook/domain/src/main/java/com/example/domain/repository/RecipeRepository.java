package com.example.domain.repository;
import com.example.domain.models.Recipe;
import com.example.domain.models.User;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> getRecipes();
    Recipe getRecipeById(int id);
    boolean addRecipe(Recipe recipe);
    boolean editRecipe(Recipe recipe);
    boolean addRecipeToFavorites(Recipe recipe);
    List<Recipe> getFavorites();
    boolean login(String username, String password);
    boolean register(User user);
}