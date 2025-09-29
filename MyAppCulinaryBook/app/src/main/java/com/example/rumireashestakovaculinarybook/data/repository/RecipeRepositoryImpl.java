package com.example.rumireashestakovaculinarybook.data.repository;

import com.example.rumireashestakovaculinarybook.domain.models.Recipe;
import com.example.rumireashestakovaculinarybook.domain.models.User;
import com.example.rumireashestakovaculinarybook.domain.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepositoryImpl implements RecipeRepository {
    private List<Recipe> testRecipes = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Recipe> favorites = new ArrayList<>();

    public RecipeRepositoryImpl() {
        testRecipes.add(new Recipe(1, "Борщ"));
        testRecipes.add(new Recipe(2, "Паста Карбонара"));
        testRecipes.add(new Recipe(3, "Картошка фри"));
        testRecipes.add(new Recipe(4, "Наггетсф"));
        testRecipes.add(new Recipe(5, "Голуби"));
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

    public boolean editRecipe(Recipe recipe) {
        for (int i = 0; i < testRecipes.size(); i++) {
            if (testRecipes.get(i).getId() == recipe.getId()) {
                testRecipes.set(i, recipe);
                return true;
            }
        }
        return false;
    }

    public boolean addRecipeToFavorites(Recipe recipe) {
        return favorites.add(recipe);
    }

    public List<Recipe> getFavorites() {
        return favorites;
    }

    @Override
    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean register(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return false;}
        }
        users.add(user);
        return true;
    }
}