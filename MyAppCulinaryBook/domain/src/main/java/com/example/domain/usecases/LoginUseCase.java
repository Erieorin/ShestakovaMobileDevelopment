package com.example.domain.usecases;

import com.example.domain.repository.RecipeRepository;

public class LoginUseCase {
    private final RecipeRepository repository;

    public LoginUseCase(RecipeRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String username, String password) {
        return repository.login(username, password);
    }
}