package com.example.rumireashestakovalesson9.domain.usecases;

import com.example.rumireashestakovalesson9.domain.models.Movie;
import com.example.rumireashestakovalesson9.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private MovieRepository movieRepository;
    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public Movie execute(){
        return movieRepository.getMovie();
    }
}