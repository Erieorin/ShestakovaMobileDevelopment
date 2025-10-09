package com.example.data.storage.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.data.storage.MovieStorage;
import com.example.data.storage.models.Movie;

import java.time.LocalDate;

public class SharedPrefMovieStorage implements MovieStorage {
    private static final String SHARED_PREFS_NAME = "shared_prefs_name";
    private static final String KEY = "movie_name";
    private static final String DATE_KEY = "movie_date";
    private static final String ID_KEY = "movie_id";

    private SharedPreferences sharedPreferences;

    public SharedPrefMovieStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public Movie get() {
        String movieName = sharedPreferences.getString(KEY, "unknown");
        String movieDate = sharedPreferences.getString(DATE_KEY, LocalDate.now().toString());
        int movieId = sharedPreferences.getInt(ID_KEY, -1);

        return new Movie(movieId, movieName, movieDate); // data-слой Movie
    }

    @Override
    public boolean save(Movie movie) {
        sharedPreferences.edit()
                .putString(KEY, movie.getName())
                .putString(DATE_KEY, LocalDate.now().toString())
                .putInt(ID_KEY, movie.getId())
                .apply();
        return true;
    }
}
