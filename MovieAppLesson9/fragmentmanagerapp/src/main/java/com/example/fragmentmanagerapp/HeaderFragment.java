package com.example.fragmentmanagerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class HeaderFragment extends Fragment {

    private ShareViewModel viewModel;

    public static HeaderFragment newInstance() {
        return new HeaderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_header, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        String[] countries = {"Россия", "США", "Германия", "Франция", "Япония"};
        ListView listView = view.findViewById(R.id.listViewCountries);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, countries);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, v, position, id) -> {
            String selected = countries[position];
            viewModel.selectItem(selected);
        });
    }
}
