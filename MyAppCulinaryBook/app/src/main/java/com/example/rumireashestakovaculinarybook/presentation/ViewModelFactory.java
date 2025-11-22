package com.example.rumireashestakovaculinarybook.presentation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.data.repository.RecipeRepositoryImpl;
import com.example.domain.repository.RecipeRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public ViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        RecipeRepository repository = new RecipeRepositoryImpl(context);
        return (T) new MainViewModel(repository);
    }
}
