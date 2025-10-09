package com.example.data.storage.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class RecipeEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public RecipeEntity(String name) {
        this.name = name;
    }
}
