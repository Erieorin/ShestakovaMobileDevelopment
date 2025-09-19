package com.example.rumireashestakovaculinarybook.domain.usecases;

import com.example.rumireashestakovaculinarybook.domain.models.User;
import com.example.rumireashestakovaculinarybook.domain.repository.RecipeRepository;

public class RegisterUseCase {
    private RecipeRepository recipeRepository;

    public RegisterUseCase(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public boolean execute(User user) {
        return recipeRepository.register(user);
    }
}