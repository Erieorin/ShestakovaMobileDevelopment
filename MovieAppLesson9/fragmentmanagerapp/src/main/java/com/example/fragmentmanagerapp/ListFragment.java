package com.example.fragmentmanagerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ListFragment extends Fragment {

    private ShareViewModel viewModel;

    public ListFragment() {
        super(R.layout.list_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(requireActivity())
                .get(ShareViewModel.class);

        ListView listView = view.findViewById(R.id.listView);

        String[] countries = {
                "Россия", "Франция", "Германия",
                "Италия", "Япония", "Канада"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                countries
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, v, position, id) ->
                viewModel.selectItem(countries[position]));
    }
}