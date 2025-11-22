package com.example.rumireashestakovalesson9.presentation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.models.Movie;
import com.example.domain.repository.MovieRepository;

import com.example.domain.usecases.SaveFilmToFavoriteUseCase;
import com.example.domain.usecases.GetFavoriteFilmUseCase;


public class MainViewModel extends ViewModel {
    private final MovieRepository movieRepository;
    public MainViewModel(MovieRepository movieRepository) {
        Log.d(MainViewModel.class.getSimpleName().toString(), "MainViewModel created");
        this.movieRepository = movieRepository;
    }
    @Override
    protected void onCleared() {
        Log.d(MainViewModel.class.getSimpleName().toString(), "MainViewModel cleared");
        super.onCleared();
    }
    public void setText(Movie movie){
        Boolean result = new SaveFilmToFavoriteUseCase(movieRepository).execute(movie);
        favoriteMovie.setValue(result.toString());
    }
    public void getText(){
        Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
        favoriteMovie.setValue(String.format("My favorite movie is %s", movie.getName()));
    }

    private final MutableLiveData<String> favoriteMovie = new MutableLiveData<>();
    public MutableLiveData<String> getFavoriteMovie() {
        return favoriteMovie;
    }
}