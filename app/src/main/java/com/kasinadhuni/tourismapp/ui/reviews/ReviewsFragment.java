package com.kasinadhuni.tourismapp.ui.reviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kasinadhuni.tourismapp.R;
import java.util.List;

public class ReviewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;
    private ReviewDatabase db;

    private EditText editTextUsername, editTextReview;
    private Button buttonSubmit;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        //  Initialize Views
        editTextUsername = root.findViewById(R.id.edit_text_username);
        editTextReview = root.findViewById(R.id.edit_text_review);
        buttonSubmit = root.findViewById(R.id.button_submit_review);
        recyclerView = root.findViewById(R.id.recycler_view_reviews);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //  Setup Room DB
        db = ReviewDatabase.getInstance(getContext());
        reviewList = db.reviewDao().getAllReviews();

        //  Dummy data if empty
        if (reviewList.isEmpty()) {
            db.reviewDao().insert(new Review("John", "Absolutely loved it!"));
            db.reviewDao().insert(new Review("Priya", "A must-visit destination."));
            db.reviewDao().insert(new Review("Ravi", "Peaceful and well maintained."));
            db.reviewDao().insert(new Review("Sara", "Loved the food nearby!"));
            reviewList = db.reviewDao().getAllReviews();
        }

        //  Adapter binding
        reviewAdapter = new ReviewAdapter(reviewList);
        recyclerView.setAdapter(reviewAdapter);

        //  Submit Review Action
        buttonSubmit.setOnClickListener(view -> {
            String user = editTextUsername.getText().toString().trim();
            String review = editTextReview.getText().toString().trim();

            if (!user.isEmpty() && !review.isEmpty()) {
                db.reviewDao().insert(new Review(user, review));
                reviewList.clear();
                reviewList.addAll(db.reviewDao().getAllReviews());
                reviewAdapter.notifyDataSetChanged();


                editTextUsername.setText("");
                editTextReview.setText("");

                Toast.makeText(getContext(), "Review submitted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please enter both name and review.", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}