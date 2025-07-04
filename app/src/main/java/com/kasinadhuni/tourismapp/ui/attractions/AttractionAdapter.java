package com.kasinadhuni.tourismapp.ui.attractions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kasinadhuni.tourismapp.R;

import java.util.List;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder> {

    private final List<Attraction> attractionList;
    private final OnItemClickListener listener;

    public AttractionAdapter(List<Attraction> list, OnItemClickListener listener) {
        this.attractionList = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_attraction, parent, false);
        return new AttractionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionViewHolder holder, int position) {
        Attraction attraction = attractionList.get(position);
        holder.title.setText(attraction.title);
        holder.description.setText(attraction.description);
        holder.image.setImageResource(attraction.imageResId);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(attraction));
    }

    @Override
    public int getItemCount() {
        return attractionList.size();
    }

    static class AttractionViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView image;

        public AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            description = itemView.findViewById(R.id.text_view_description);
            image = itemView.findViewById(R.id.image_view_attraction);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Attraction attraction);
    }
}