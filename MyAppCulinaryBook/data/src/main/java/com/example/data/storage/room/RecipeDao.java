package com.example.data.storage.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDao {
    @Insert
    void insert(RecipeEntity recipe);

    @Query("SELECT * FROM recipes")
    List<RecipeEntity> getAll();
}
