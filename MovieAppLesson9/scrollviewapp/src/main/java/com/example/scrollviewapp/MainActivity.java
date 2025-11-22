package com.example.scrollviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout wrapper = findViewById(R.id.wrapper);
        LayoutInflater inflater = LayoutInflater.from(this);

        int value = 1;
        for (int i = 0; i < 100; i++) {
            View view = inflater.inflate(R.layout.item, wrapper, false);
            TextView text = view.findViewById(R.id.textView);
            text.setText(String.format("Элемент %d: %d", i + 1, value));
            wrapper.addView(view);
            value *= 2;
        }
    }
}
