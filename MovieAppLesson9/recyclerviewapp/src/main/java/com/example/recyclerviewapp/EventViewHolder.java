package com.example.recyclerviewapp;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView titleView;
    private TextView descriptionView;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageEvent);
        titleView = itemView.findViewById(R.id.textEventTitle);
        descriptionView = itemView.findViewById(R.id.textEventDescription);
    }

    public ImageView getImageView() { return imageView; }

    public TextView getTitleView() { return titleView; }

    public TextView getDescriptionView() { return descriptionView; }
}