package com.example.rumireashestakovaculinarybook.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AuthViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;

    public AuthViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AuthViewModel(application);
    }
}