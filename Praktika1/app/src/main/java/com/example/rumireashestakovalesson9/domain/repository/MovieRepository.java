package com.example.rumireashestakovalesson9.domain.repository;

import com.example.rumireashestakovalesson9.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);
    public Movie getMovie();
}
