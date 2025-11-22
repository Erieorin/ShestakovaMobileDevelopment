package com.example.fragmentmanagerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class DetailsFragment extends Fragment {

    private ShareViewModel viewModel;
    private TextView textViewDetails;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewDetails = view.findViewById(R.id.textViewDetails);
        viewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        viewModel.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            textViewDetails.setText("Вы выбрали: " + item);
        });
    }
}