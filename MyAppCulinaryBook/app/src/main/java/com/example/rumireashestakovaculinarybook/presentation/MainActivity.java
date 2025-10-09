package com.example.rumireashestakovaculinarybook.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.data.repository.RecipeRepositoryImpl;
import com.example.domain.models.Recipe;
import com.example.domain.usecases.AddRecipeUseCase;
import com.example.domain.usecases.GetRecipesUseCase;
import com.example.rumireashestakovaculinarybook.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvUserEmail, tvResult;
    private Button btnGetRecipes, btnAddRecipe, btnLogout;
    private FirebaseAuth auth;

    private GetRecipesUseCase getRecipesUseCase;
    private AddRecipeUseCase addRecipeUseCase;
    private RecipeRepositoryImpl repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUserEmail = findViewById(R.id.tvUserEmail);
        tvResult = findViewById(R.id.tvResult);
        btnGetRecipes = findViewById(R.id.btnGetRecipes);
        btnAddRecipe = findViewById(R.id.btnAddRecipe);
        btnLogout = findViewById(R.id.btnLogout);

        auth = FirebaseAuth.getInstance();
        repository = new RecipeRepositoryImpl(this);

        getRecipesUseCase = new GetRecipesUseCase(repository);
        addRecipeUseCase = new AddRecipeUseCase(repository);

        String email = repository.getCurrentUser();
        tvUserEmail.setText(email != null ? "Вы вошли как: " + email : "Пользователь не найден");

        btnGetRecipes.setOnClickListener(v -> showRecipes());
        btnAddRecipe.setOnClickListener(v -> addRecipe());
        btnLogout.setOnClickListener(v -> logout());
    }

    private void showRecipes() {
        List<Recipe> recipes = getRecipesUseCase.execute();
        if (recipes.isEmpty()) {
            tvResult.setText("Рецептов нет");
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (Recipe r : recipes) {
            builder.append(r.getId()).append(". ").append(r.getName()).append("\n");
        }
        tvResult.setText(builder.toString());
    }

    private void addRecipe() {
        Recipe newRecipe = new Recipe(0, "Новый рецепт #" + System.currentTimeMillis());
        boolean result = addRecipeUseCase.execute(newRecipe);
        Toast.makeText(this, result ? "Рецепт добавлен!" : "Ошибка", Toast.LENGTH_SHORT).show();
        showRecipes();
    }

    private void logout() {
        auth.signOut();
        repository.clearUser();
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
