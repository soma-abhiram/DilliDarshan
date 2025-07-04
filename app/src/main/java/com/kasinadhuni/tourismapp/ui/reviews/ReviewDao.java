package com.kasinadhuni.tourismapp.ui.reviews;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ReviewDao {

    @Insert
    void insert(Review review);

    @Query("SELECT * FROM Review")
    List<Review> getAllReviews();
}