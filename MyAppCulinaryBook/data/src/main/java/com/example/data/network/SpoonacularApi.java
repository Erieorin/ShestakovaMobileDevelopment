package com.example.data.network;

import com.example.domain.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpoonacularApi {

    @GET("recipes/random")
    Call<RandomRecipesResponse> getRandomRecipes(
            @Query("number") int number,
            @Query("apiKey") String apiKey
    );

    class RandomRecipesResponse {
        public List<Recipe> recipes;
    }

    @GET("recipes/{id}/information")
    Call<Recipe> getRecipeInformation(
            @Path("id") int id,
            @Query("includeNutrition") boolean includeNutrition,
            @Query("apiKey") String apiKey
    );
}
