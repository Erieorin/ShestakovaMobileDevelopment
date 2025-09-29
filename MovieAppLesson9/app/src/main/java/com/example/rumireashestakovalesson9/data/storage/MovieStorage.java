package com.example.rumireashestakovalesson9.data.storage;
import com.example.rumireashestakovalesson9.data.storage.models.Movie;
public interface MovieStorage {
    public Movie get();
    public boolean save(Movie movie);
}
