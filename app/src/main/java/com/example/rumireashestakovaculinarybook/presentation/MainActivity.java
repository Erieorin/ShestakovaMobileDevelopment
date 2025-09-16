package com.example.rumireashestakovaculinarybook.presentation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rumireashestakovaculinarybook.R;
import com.example.rumireashestakovaculinarybook.data.repository.RecipeRepositoryImpl;
import com.example.rumireashestakovaculinarybook.domain.usecases.GetRecipeListUseCase;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);

        RecipeRepositoryImpl recipeRepository = new RecipeRepositoryImpl();
        GetRecipeListUseCase getRecipeListUseCase = new GetRecipeListUseCase(recipeRepository);

        StringBuilder builder = new StringBuilder("Рецепты:\n");
        for (var recipe : getRecipeListUseCase.execute()) {
            builder.append(recipe.getId()).append(". ").append(recipe.getName()).append("\n");
        }
        textView.setText(builder.toString());
    }
}