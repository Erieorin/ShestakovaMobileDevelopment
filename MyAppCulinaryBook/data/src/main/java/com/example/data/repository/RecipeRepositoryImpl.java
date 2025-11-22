package com.example.data.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.data.network.MockNetworkApi;
import com.example.data.network.RetrofitClient;
import com.example.data.network.SpoonacularApi;
import com.example.data.storage.SharedPrefStorage;
import com.example.data.storage.room.AppDatabase;
import com.example.data.storage.room.RecipeDao;
import com.example.data.storage.room.RecipeEntity;
import com.example.domain.models.Recipe;
import com.example.domain.models.User;
import com.example.domain.repository.RecipeRepository;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepositoryImpl implements RecipeRepository {

    private final SharedPrefStorage sharedPrefs;
    private final RecipeDao recipeDao;
    private final MockNetworkApi api;
    private final FirebaseAuth auth;

    public RecipeRepositoryImpl(Context context) {
        sharedPrefs = new SharedPrefStorage(context);
        recipeDao = Room.databaseBuilder(context, AppDatabase.class, "recipes.db")
                .allowMainThreadQueries()
                .build()
                .recipeDao();
        api = new MockNetworkApi();
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean login(String username, String password) {
        sharedPrefs.saveUser(username);
        auth.signInWithEmailAndPassword(username, password);
        return true;
    }

    @Override
    public boolean register(User user) {
        sharedPrefs.saveUser(user.getUsername());
        auth.createUserWithEmailAndPassword(user.getUsername(), user.getPassword());
        return true;
    }

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        try {
            SpoonacularApi api = RetrofitClient.getApi();
            retrofit2.Response<SpoonacularApi.RandomRecipesResponse> response =
                    api.getRandomRecipes(5, "b54ea24d91614381b44882aada110833").execute();

            if (response.isSuccessful() && response.body() != null) {
                recipes.addAll(response.body().recipes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<RecipeEntity> entities = recipeDao.getAll();
        for (RecipeEntity e : entities) {
            recipes.add(new Recipe(e.id, e.name));
        }

        return recipes;
    }

    @Override
    public Recipe getRecipeById(int id) {
        List<RecipeEntity> entities = recipeDao.getAll();
        for (RecipeEntity e : entities) {
            if (e.id == id) return new Recipe(e.id, e.name);
        }
        return null;
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        recipeDao.insert(new RecipeEntity(recipe.getName()));
        return true;
    }

    @Override
    public boolean editRecipe(Recipe recipe) {
        return true;
    }

    @Override
    public boolean addRecipeToFavorites(Recipe recipe) {
        return true;
    }

    @Override
    public List<Recipe> getFavorites() {
        return new ArrayList<>();
    }

    public String getCurrentUser() {
        return sharedPrefs.getUser();
    }

    public void clearUser() {
        sharedPrefs.clear();
    }
}
