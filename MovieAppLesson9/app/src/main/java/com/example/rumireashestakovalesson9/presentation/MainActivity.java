package com.example.rumireashestakovalesson9.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.rumireashestakovalesson9.R;
import com.example.data.repository.MovieRepositoryImpl;
import com.example.data.storage.MovieStorage;
import com.example.data.storage.sharedprefs.SharedPrefMovieStorage;
import com.example.domain.models.Movie;
import com.example.domain.repository.MovieRepository;
import com.example.domain.usecases.GetFavoriteFilmUseCase;
import com.example.domain.usecases.SaveFilmToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText text = findViewById(R.id.editTextMovie);
        TextView textView = findViewById(R.id.textViewMovie);

        Log.d(MainActivity.class.getSimpleName().toString(), "MainActivity created");
        mainViewModel = new ViewModelProvider(this, new ViewModelFactory(this)).get(MainViewModel.class);

        mainViewModel.getFavoriteMovie().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

        findViewById(R.id.buttonSaveMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.setText(new Movie(2, text.getText().toString()));
            }
        });

        findViewById(R.id.buttonGetMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.getText();
            }
        });

    }
}