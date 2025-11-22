package com.example.recyclerviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoricalEventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<HistoricalEvent> events;
    private Context context;

    public HistoricalEventAdapter(List<HistoricalEvent> events) {
        this.events = events;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.event_item_view, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        HistoricalEvent event = events.get(position);

        holder.getTitleView().setText(event.getTitle());
        holder.getDescriptionView().setText(event.getDescription());

        String pkg = context.getPackageName();
        int resID = context.getResources().getIdentifier(event.getImageName(), "drawable", pkg);

        holder.getImageView().setImageResource(resID);

        holder.itemView.setOnClickListener(v ->
                android.widget.Toast.makeText(context,
                        "Вы выбрали: " + event.getTitle(),
                        android.widget.Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}