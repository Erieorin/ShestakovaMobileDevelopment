package com.example.rumireashestakovaculinarybook.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.models.Recipe;
import com.example.rumireashestakovaculinarybook.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    private List<Recipe> items = new ArrayList<>();

    public void setItems(List<Recipe> recipes) {
        this.items = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = items.get(position);
        holder.tvTitle.setText(recipe.getName());

        if (recipe.getImageUrl() != null && !recipe.getImageUrl().isEmpty()) {
            Picasso.get()
                    .load(recipe.getImageUrl())
                    .into(holder.ivRecipeImage);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRecipeImage;
        TextView tvTitle;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRecipeImage = itemView.findViewById(R.id.ivRecipeImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
