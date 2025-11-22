package com.example.retrofitapp;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoViewHolder extends RecyclerView.ViewHolder {

    TextView textViewTitle;
    CheckBox checkBoxCompleted;
    ImageView imageTodo;

    public TodoViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        checkBoxCompleted = itemView.findViewById(R.id.checkBoxCompleted);
        imageTodo = itemView.findViewById(R.id.imageTodo);
    }
}