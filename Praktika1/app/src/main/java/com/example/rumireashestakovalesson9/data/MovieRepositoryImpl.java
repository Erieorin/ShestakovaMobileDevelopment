package com.example.rumireashestakovalesson9.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.rumireashestakovalesson9.domain.models.Movie;
import com.example.rumireashestakovalesson9.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {

    private static final String PREFS_NAME = "movie_prefs";
    private static final String KEY_MOVIE_NAME = "favorite_movie_name";
    private static final String KEY_MOVIE_ID = "favorite_movie_id";

    private SharedPreferences sharedPreferences;

    public MovieRepositoryImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean saveMovie(Movie movie) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_MOVIE_ID, movie.getId());
        editor.putString(KEY_MOVIE_NAME, movie.getName());
        return editor.commit();
    }

    @Override
    public Movie getMovie() {
        int id = sharedPreferences.getInt(KEY_MOVIE_ID, -1);
        String name = sharedPreferences.getString(KEY_MOVIE_NAME, "Нет сохранённого фильма");
        return new Movie(id, name);
    }
}
