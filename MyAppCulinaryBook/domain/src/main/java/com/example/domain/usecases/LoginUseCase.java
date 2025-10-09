package com.example.domain.usecases;

import com.example.domain.repository.RecipeRepository;

public class LoginUseCase {
    private RecipeRepository recipeRepository;

    public LoginUseCase(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public boolean execute(String username, String password) {
        return recipeRepository.login(username, password);
    }
}