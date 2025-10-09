package com.example.data.repository;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.data.storage.MovieStorage;
import com.example.data.storage.models.Movie;
import com.example.domain.repository.MovieRepository;

import java.time.LocalDate;

public class MovieRepositoryImpl implements MovieRepository {

    private final MovieStorage storage;

    public MovieRepositoryImpl(MovieStorage storage) {
        this.storage = storage;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean saveMovie(com.example.domain.models.Movie movie) {
        storage.save(mapToStorage(movie));
        return true;
    }

    @Override
    public com.example.domain.models.Movie getMovie() {
        Movie movie = storage.get();
        return mapToDomain(movie);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Movie mapToStorage(com.example.domain.models.Movie movie) {
        return new Movie(movie.getId(), movie.getName(), LocalDate.now().toString());
    }

    private com.example.domain.models.Movie mapToDomain(Movie movie) {
        return new com.example.domain.models.Movie(
                movie.getId(),
                movie.getName()
        );
    }
}
