package com.example.rumireashestakovaculinarybook.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.models.Recipe;
import com.example.rumireashestakovaculinarybook.R;

import java.util.List;

public class RecipesFragment extends Fragment {

    private MainViewModel mainViewModel;
    private RecyclerView rvRecipes;
    private RecipesAdapter adapter;
    private Button btnGetRecipes, btnAddRecipe;

    public RecipesFragment() {
        super(R.layout.fragment_recipes);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvRecipes = view.findViewById(R.id.rvRecipes);
        btnGetRecipes = view.findViewById(R.id.btnGetRecipes);
        btnAddRecipe = view.findViewById(R.id.btnAddRecipe);

        adapter = new RecipesAdapter();
        rvRecipes.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvRecipes.setAdapter(adapter);

        mainViewModel = new ViewModelProvider(requireActivity(), new ViewModelFactory(requireContext()))
                .get(MainViewModel.class);

        mainViewModel.getCombinedRecipes()
                .observe(getViewLifecycleOwner(), adapter::setItems);

        btnGetRecipes.setOnClickListener(v -> {
            new Thread(() -> {
                List<Recipe> recipes = mainViewModel.loadRecipesFromApi();
                requireActivity().runOnUiThread(() -> adapter.setItems(recipes));
            }).start();
        });

        btnAddRecipe.setOnClickListener(v -> {
            Recipe recipe = new Recipe(
                    0,
                    "Новый рецепт #" + System.currentTimeMillis()
            );
            mainViewModel.addRecipe(recipe);
            Toast.makeText(requireContext(), "Рецепт добавлен", Toast.LENGTH_SHORT).show();
        });
    }
}
