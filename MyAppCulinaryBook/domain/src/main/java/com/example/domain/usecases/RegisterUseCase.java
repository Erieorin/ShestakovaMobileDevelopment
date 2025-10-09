package com.example.domain.usecases;

import com.example.domain.models.User;
import com.example.domain.repository.RecipeRepository;

public class RegisterUseCase {
    private RecipeRepository recipeRepository;

    public RegisterUseCase(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public boolean execute(User user) {
        return recipeRepository.register(user);
    }
}