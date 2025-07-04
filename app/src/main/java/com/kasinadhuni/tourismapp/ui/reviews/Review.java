package com.kasinadhuni.tourismapp.ui.reviews;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Review {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String reviewer;
    public String comment;

    public Review(String reviewer, String comment) {
        this.reviewer = reviewer;
        this.comment = comment;
    }
}