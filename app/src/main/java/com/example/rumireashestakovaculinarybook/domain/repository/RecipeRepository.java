package com.example.rumireashestakovaculinarybook.domain.repository;
import com.example.rumireashestakovaculinarybook.domain.models.Recipe;
import java.util.List;

public interface RecipeRepository {
    List<Recipe> getRecipes();
    Recipe getRecipeById(int id);
    boolean addRecipe(Recipe recipe);
}