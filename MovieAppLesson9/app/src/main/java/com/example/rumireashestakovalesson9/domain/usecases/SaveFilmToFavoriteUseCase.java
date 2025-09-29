package com.example.rumireashestakovalesson9.domain.usecases;

import com.example.rumireashestakovalesson9.domain.models.Movie;
import com.example.rumireashestakovalesson9.domain.repository.MovieRepository;

public class SaveFilmToFavoriteUseCase {
    private MovieRepository movieRepository;
    public SaveFilmToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public boolean execute(Movie movie){
        return movieRepository.saveMovie(movie);
    }
}
