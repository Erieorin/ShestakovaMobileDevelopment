package com.example.rumireashestakovaculinarybook.presentation;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.models.Recipe;
import com.example.domain.repository.RecipeRepository;
import com.example.domain.usecases.AddRecipeUseCase;
import com.example.domain.usecases.GetRecipesUseCase;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final RecipeRepository recipeRepository;
    private AddRecipeUseCase addRecipeUseCase;
    private GetRecipesUseCase getRecipesUseCase;

    private final MutableLiveData<List<Recipe>> localRecipes = new MutableLiveData<>();
    private final MutableLiveData<List<Recipe>> networkRecipes = new MutableLiveData<>();
    private final MediatorLiveData<List<Recipe>> combinedRecipes = new MediatorLiveData<>();
    public MainViewModel(RecipeRepository recipeRepository) {
        Log.d(MainViewModel.class.getSimpleName().toString(), "MainViewModel created");
        this.recipeRepository = recipeRepository;
        this.getRecipesUseCase = new GetRecipesUseCase(recipeRepository);
        this.addRecipeUseCase = new AddRecipeUseCase(recipeRepository);

        combinedRecipes.addSource(localRecipes, local -> combineData());
        combinedRecipes.addSource(networkRecipes, net -> combineData());
    }
    @Override
    protected void onCleared() {
        Log.d(MainViewModel.class.getSimpleName().toString(), "MainViewModel cleared");
        super.onCleared();
    }

    private void combineData() {
        List<Recipe> all = new ArrayList<>();
        if (localRecipes.getValue() != null) all.addAll(localRecipes.getValue());
        if (networkRecipes.getValue() != null) all.addAll(networkRecipes.getValue());
        combinedRecipes.setValue(all);
    }

    public void loadRecipes() {
        List<Recipe> allRecipes = getRecipesUseCase.execute();
        List<Recipe> local = new ArrayList<>();
        List<Recipe> network = new ArrayList<>();

        for (Recipe r : allRecipes) {
            if (r.getId() >= 100) network.add(r);
            else local.add(r);
        }

        localRecipes.setValue(local);
        networkRecipes.setValue(network);
    }

    public void addRecipe(Recipe recipe) {
        addRecipeUseCase.execute(recipe);
        loadRecipes();
    }

    public LiveData<List<Recipe>> getCombinedRecipes() {
        return combinedRecipes;
    }

    public List<Recipe> loadRecipesFromApi() {
        try {
            return recipeRepository.getRecipes();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
