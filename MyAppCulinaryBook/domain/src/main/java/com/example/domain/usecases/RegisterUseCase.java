package com.example.domain.usecases;

import com.example.domain.models.User;
import com.example.domain.repository.RecipeRepository;

public class RegisterUseCase {
    private final RecipeRepository repository;

    public RegisterUseCase(RecipeRepository repository) {
        this.repository = repository;
    }

    public boolean execute(User user) {
        return repository.register(user);
    }
}