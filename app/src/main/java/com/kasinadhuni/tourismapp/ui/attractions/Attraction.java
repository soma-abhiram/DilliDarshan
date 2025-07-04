package com.kasinadhuni.tourismapp.ui.attractions;

public class Attraction {
    public String title;
    public String description;
    public int imageResId;
    public double latitude;
    public double longitude;

    public Attraction(String title, String description, int imageResId, double latitude, double longitude) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}