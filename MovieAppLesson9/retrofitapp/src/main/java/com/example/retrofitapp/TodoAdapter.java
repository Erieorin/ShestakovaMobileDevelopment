package com.example.retrofitapp;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {

    private Context context;
    private List<Todo> todos;
    private ApiService apiService = RetrofitClient.getApi();

    public TodoAdapter(Context context, List<Todo> todos) {
        this.context = context;
        this.todos = todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);

        holder.textViewTitle.setText(todo.getTitle());
        holder.checkBoxCompleted.setChecked(todo.getCompleted());

        Picasso.get()
                .load(generateImageUrl(todo.getId()))
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageTodo);

        holder.checkBoxCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            todo.setCompleted(isChecked);

            apiService.updateTodo(todo.getId(), todo)
                    .enqueue(new Callback<Todo>() {
                        @Override
                        public void onResponse(Call<Todo> call, Response<Todo> response) {
                            Log.d("API", "Updated: " + response.code());
                        }

                        @Override
                        public void onFailure(Call<Todo> call, Throwable t) {
                            Toast.makeText(context, "Ошибка: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private String generateImageUrl(int id) {
        int width = 200 + (id % 3) * 100;
        int height = 150 + (id % 3) * 75;
        return "https://picsum.photos/" + width + "/" + height + "?random=" + id;
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }
}