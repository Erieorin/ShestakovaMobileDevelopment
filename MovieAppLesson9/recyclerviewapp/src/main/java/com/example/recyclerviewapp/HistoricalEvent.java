package com.example.recyclerviewapp;

public class HistoricalEvent {
    private String title;
    private String description;
    private String imageName;

    public HistoricalEvent(String title, String description, String imageName) {
        this.title = title;
        this.description = description;
        this.imageName = imageName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageName() {
        return imageName;
    }
}