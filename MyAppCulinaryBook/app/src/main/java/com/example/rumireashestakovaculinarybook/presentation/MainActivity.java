package com.example.rumireashestakovaculinarybook.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.models.Recipe;
import com.example.rumireashestakovaculinarybook.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private AuthViewModel authViewModel;
    private TextView tvUserEmail;
    private Button btnGetRecipes, btnAddRecipe, btnLogout;

    private RecyclerView rvRecipes;
    private RecipesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUserEmail = findViewById(R.id.tvUserEmail);
        btnGetRecipes = findViewById(R.id.btnGetRecipes);
        btnAddRecipe = findViewById(R.id.btnAddRecipe);
        btnLogout = findViewById(R.id.btnLogout);

        mainViewModel = new ViewModelProvider(this, new ViewModelFactory(this))
                .get(MainViewModel.class);
        authViewModel = new ViewModelProvider(this, new AuthViewModelFactory(getApplication()))
                .get(AuthViewModel.class);

        authViewModel.isUserLoggedIn().observe(this, loggedIn -> {
            if (loggedIn == null || !loggedIn) {
                startActivity(new Intent(this, AuthActivity.class));
                finish();
            }
        });

        authViewModel.getCurrentUserEmail().observe(this, email ->
                tvUserEmail.setText(email != null ? "Вы вошли как: " + email : "Пользователь не найден")
        );

        rvRecipes = findViewById(R.id.rvRecipes);
        adapter = new RecipesAdapter();
        rvRecipes.setAdapter(adapter);
        rvRecipes.setLayoutManager(new LinearLayoutManager(this));

        mainViewModel.getCombinedRecipes().observe(this, recipes -> {
            adapter.setItems(recipes);
        });

        btnGetRecipes.setOnClickListener(v -> {
            new Thread(() -> {
                List<Recipe> recipes = mainViewModel.loadRecipesFromApi();
                runOnUiThread(() -> adapter.setItems(recipes));
            }).start();
        });

        btnAddRecipe.setOnClickListener(v -> {
            Recipe newRecipe = new Recipe(0, "Новый рецепт #" + System.currentTimeMillis());
            mainViewModel.addRecipe(newRecipe);
            Toast.makeText(this, "Рецепт добавлен!", Toast.LENGTH_SHORT).show();
        });

        btnLogout.setOnClickListener(v -> authViewModel.logout());
    }
}
