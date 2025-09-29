package com.example.rumireashestakovalesson9.presentation;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rumireashestakovalesson9.R;
import com.example.rumireashestakovalesson9.data.repository.MovieRepositoryImpl;
import com.example.rumireashestakovalesson9.data.storage.MovieStorage;
import com.example.rumireashestakovalesson9.data.storage.sharedprefs.SharedPrefMovieStorage;
import com.example.rumireashestakovalesson9.domain.models.Movie;
import com.example.rumireashestakovalesson9.domain.repository.MovieRepository;
import com.example.rumireashestakovalesson9.domain.usecases.GetFavoriteFilmUseCase;
import com.example.rumireashestakovalesson9.domain.usecases.SaveFilmToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText text = findViewById(R.id.editTextMovie);
        TextView textView = findViewById(R.id.textViewMovie);

        MovieStorage sharedPrefMovieStorage = new SharedPrefMovieStorage(this);
        MovieRepository movieRepository = new MovieRepositoryImpl(sharedPrefMovieStorage);

        findViewById(R.id.buttonSaveMovie).setOnClickListener(view -> {
            Boolean result = new SaveFilmToFavoriteUseCase(movieRepository).execute(new Movie(2, text.getText().toString()));
            textView.setText(String.format("Save result %s", result));
        });
        findViewById(R.id.buttonGetMovie).setOnClickListener(view -> {
            Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
            textView.setText(String.format("Save result %s", movie.getName()));
        });
    }
}