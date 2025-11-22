package com.example.rumireashestakovalesson9.presentation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.data.repository.MovieRepositoryImpl;
import com.example.data.storage.MovieStorage;
import com.example.data.storage.sharedprefs.SharedPrefMovieStorage;
import com.example.domain.repository.MovieRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;
    public ViewModelFactory(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        MovieStorage sharedPrefMovieStorage = new
                SharedPrefMovieStorage(context);
        MovieRepository movieRepository = new MovieRepositoryImpl(sharedPrefMovieStorage);
        return (T) new MainViewModel(movieRepository);
    }
}
