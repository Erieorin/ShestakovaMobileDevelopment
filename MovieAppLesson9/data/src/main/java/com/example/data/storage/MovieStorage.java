package com.example.data.storage;
import com.example.data.storage.models.Movie;
public interface MovieStorage {
    public Movie get();
    public boolean save(Movie movie);
}
