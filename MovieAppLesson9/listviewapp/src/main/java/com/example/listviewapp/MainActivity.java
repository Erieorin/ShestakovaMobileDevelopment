package com.example.listviewapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] authors = {
            "Толстой – Война и мир",
            "Достоевский – Идиот",
            "Булгаков – Мастер и Маргарита",
            "Пушкин – Евгений Онегин",
            "Оруэлл – 1984",
            "Хаксли – О дивный новый мир",
            "Брэдбери – 451 градус по Фаренгейту",
            "Лем – Солярис",
            "Кинг – Оно",
            "Кинг – Сияние",
            "Азимов – Основание",
            "Кларк – Одиссея 2001",
            "Гюго – Отверженные",
            "Сервантес – Дон Кихот",
            "Кафка – Процесс",
            "Камю – Чума",
            "Сапковский – Ведьмак",
            "Роулинг – Гарри Поттер",
            "Мартин – Игра престолов",
            "Хемингуэй – Старик и море",
            "Фицджеральд – Великий Гэтсби",
            "Диккенс – Оливер Твист",
            "Верн – 20 000 лье под водой",
            "Стругацкие – Пикник на обочине",
            "Толкиен – Властелин колец",
            "Лондон – Мартин Иден",
            "Ремарк – Три товарища",
            "Остин – Гордость и предубеждение",
            "Жюль Верн – Таинственный остров",
            "Дойл – Собака Баскервилей",
            "Чехов – Палата №6",
            "Гоголь – Мёртвые души",
            "Симак – Город",
            "Сервантес – Дон Кихот",
            "Кафка – Процесс",
            "Камю – Чума",
            "Сапковский – Ведьмак",
            "Роулинг – Гарри Поттер",
            "Мартин – Игра престолов",
            "Хемингуэй – Старик и море",
            "Фицджеральд – Великий Гэтсби",
            "Диккенс – Оливер Твист",
            "Верн – 20 000 лье под водой",
            "Стругацкие – Пикник на обочине",
            "Толкиен – Властелин колец",
            "Лондон – Мартин Иден",
            "Ремарк – Три товарища",
            "Остин – Гордость и предубеждение",
            "Жюль Верн – Таинственный остров",
            "Дойл – Собака Баскервилей",
            "Чехов – Палата №6",
            "Гоголь – Мёртвые души",
            "Симак – Город"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = findViewById(R.id.book_list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.item_book,
                R.id.textAuthor,
                authors
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                TextView author = view.findViewById(R.id.textAuthor);
                TextView book = view.findViewById(R.id.textBook);

                String[] parts = getItem(position).split(" – ");
                author.setText(parts[0]);
                book.setText(parts[1]);

                return view;
            }
        };

        list.setAdapter(adapter);
    }
}
