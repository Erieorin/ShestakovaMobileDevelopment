package com.example.retrofitapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        todoAdapter = new TodoAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(todoAdapter);

        ApiService api = RetrofitClient.getApi();
        api.getTodos().enqueue(new Callback<java.util.List<Todo>>() {
            @Override
            public void onResponse(Call<java.util.List<Todo>> call, Response<java.util.List<Todo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    todoAdapter.setTodos(response.body());
                    todoAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<java.util.List<Todo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Ошибка: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
