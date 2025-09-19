package com.example.rumireashestakovaculinarybook.domain.repository;
import com.example.rumireashestakovaculinarybook.domain.models.Recipe;
import com.example.rumireashestakovaculinarybook.domain.models.User;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> getRecipes();
    Recipe getRecipeById(int id);
    boolean addRecipe(Recipe recipe);
    boolean editRecipe(Recipe recipe);
    boolean login(String username, String password);
    boolean register(User user);
}