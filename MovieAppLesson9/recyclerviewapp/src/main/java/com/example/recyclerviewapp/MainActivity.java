package com.example.recyclerviewapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<HistoricalEvent> events = getEvents();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewEvents);
        recyclerView.setAdapter(new HistoricalEventAdapter(events));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<HistoricalEvent> getEvents() {
        List<HistoricalEvent> list = new ArrayList<>();

        list.add(new HistoricalEvent("Великое переселение народов",
                "Крупнейшее перемещение племён в IV–VII веках.",
                "event_1"));

        list.add(new HistoricalEvent("Падение Римской империи",
                "476 год — завершение античной эпохи.",
                "event_2"));

        list.add(new HistoricalEvent("Крещение Руси",
                "988 год — выбор Православия князем Владимиром.",
                "event_3"));

        list.add(new HistoricalEvent("Открытие Америки",
                "1492 — Колумб достиг Нового Света.",
                "event_4"));

        list.add(new HistoricalEvent("Отмена крепостного права",
                "1861 — реформы Александра II.",
                "event_5"));

        list.add(new HistoricalEvent("Полёт Гагарина",
                "12 апреля 1961 — первый человек в космосе.",
                "event_6"));

        return list;
    }
}