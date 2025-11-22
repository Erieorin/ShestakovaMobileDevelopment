package com.example.domain.models;

import com.google.gson.annotations.SerializedName;

public class Recipe {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String name;

    @SerializedName("image")
    private String imageUrl;

    public Recipe() {}

    public Recipe(int id, String name) {
        this.id = id;
        this.name = name;
        this.imageUrl = null;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getImageUrl() { return imageUrl; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
