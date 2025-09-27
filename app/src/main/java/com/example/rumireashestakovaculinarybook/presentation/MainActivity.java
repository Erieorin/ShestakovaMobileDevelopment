package com.example.rumireashestakovaculinarybook.presentation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rumireashestakovaculinarybook.R;
import com.example.rumireashestakovaculinarybook.data.repository.RecipeRepositoryImpl;
import com.example.rumireashestakovaculinarybook.domain.models.Recipe;
import com.example.rumireashestakovaculinarybook.domain.models.User;
import com.example.rumireashestakovaculinarybook.domain.repository.RecipeRepository;
import com.example.rumireashestakovaculinarybook.domain.usecases.AddRecipeToFavoritesUseCase;
import com.example.rumireashestakovaculinarybook.domain.usecases.AddRecipeUseCase;
import com.example.rumireashestakovaculinarybook.domain.usecases.EditRecipeUseCase;
import com.example.rumireashestakovaculinarybook.domain.usecases.GetFavoriteRecipesUseCase;
import com.example.rumireashestakovaculinarybook.domain.usecases.GetRecipeByIdUseCase;
import com.example.rumireashestakovaculinarybook.domain.usecases.GetRecipesUseCase;
import com.example.rumireashestakovaculinarybook.domain.usecases.LoginUseCase;
import com.example.rumireashestakovaculinarybook.domain.usecases.RegisterUseCase;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private GetRecipesUseCase getRecipesUseCase;
    private AddRecipeUseCase addRecipeUseCase;
    private EditRecipeUseCase editRecipeUseCase;
    private LoginUseCase loginUseCase;
    private RegisterUseCase registerUseCase;
    private AddRecipeToFavoritesUseCase addRecipeToFavoritesUseCase;
    private GetFavoriteRecipesUseCase getFavoriteRecipesUseCase;
    private GetRecipeByIdUseCase getRecipeByIdUseCase;

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeRepository repository = new RecipeRepositoryImpl();

        getRecipesUseCase = new GetRecipesUseCase(repository);
        addRecipeUseCase = new AddRecipeUseCase(repository);
        editRecipeUseCase = new EditRecipeUseCase(repository);
        loginUseCase = new LoginUseCase(repository);
        registerUseCase = new RegisterUseCase(repository);
        addRecipeToFavoritesUseCase = new AddRecipeToFavoritesUseCase(repository);
        getFavoriteRecipesUseCase = new GetFavoriteRecipesUseCase(repository);
        getRecipeByIdUseCase = new GetRecipeByIdUseCase(repository);

        tvResult = findViewById(R.id.tvResult);

        Button btnGetRecipes = findViewById(R.id.btnGetRecipes);
        Button btnAddRecipe = findViewById(R.id.btnAddRecipe);
        Button btnEditRecipe = findViewById(R.id.btnEditRecipe);
        Button btnAddFavorites = findViewById(R.id.btnAddFavorites);
        Button btnGetFavorites = findViewById(R.id.btnGetFavorites);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnGetRecipes.setOnClickListener(v -> showRecipes());
        btnAddRecipe.setOnClickListener(v -> addRecipe());
        btnEditRecipe.setOnClickListener(v -> editRecipe());
        btnAddFavorites.setOnClickListener(v -> addRecipeToFavorites());
        btnGetFavorites.setOnClickListener(v -> getFavorites());
        btnLogin.setOnClickListener(v -> login());
        btnRegister.setOnClickListener(v -> register());
    }

    private void showRecipes() {
        List<Recipe> recipes = getRecipesUseCase.execute();
        StringBuilder builder = new StringBuilder();
        for (Recipe r : recipes) {
            builder.append(r.getId()).append(": ").append(r.getName()).append("\n");
        }
        tvResult.setText(builder.toString());
    }
    private void addRecipe() {
        Recipe newRecipe = new Recipe(3, "Суп Харчо");
        boolean result = addRecipeUseCase.execute(newRecipe);
        Toast.makeText(this, result ? "Рецепт добавлен" : "Не удалось добавить", Toast.LENGTH_SHORT).show();
        showRecipes();
    }

    private void editRecipe() {
        Recipe editedRecipe = new Recipe(1, "Борщ с говядиной");
        boolean result = editRecipeUseCase.execute(editedRecipe);
        Toast.makeText(this, result ? "Рецепт отредактирован" : "Не удалось отредактировать", Toast.LENGTH_SHORT).show();
        showRecipes();
    }

    private void addRecipeToFavorites() {
        Recipe favRecipe = getRecipeByIdUseCase.execute(2);
        boolean result = addRecipeToFavoritesUseCase.execute(favRecipe);
        Toast.makeText(this, result ? "Рецепт добавлен в избранное" : "Не удалось добавить рецепт в избранное", Toast.LENGTH_SHORT).show();
    }

    private void getFavorites() {
        List<Recipe> recipes = getFavoriteRecipesUseCase.execute();
        StringBuilder builder = new StringBuilder();
        for (Recipe r : recipes) {
            builder.append(r.getId()).append(": ").append(r.getName()).append("\n");
        }
        tvResult.setText(builder.toString());
    }

    private void login() {
        boolean success = loginUseCase.execute("admin", "1234");
        Toast.makeText(this, success ? "Вход успешен" : "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
    }
    private void register() {
        User newUser = new User("user1", "pass1");
        boolean success = registerUseCase.execute(newUser);
        Toast.makeText(this, success ? "Регистрация успешна" : "Пользователь уже существует", Toast.LENGTH_SHORT).show();
    }
}
