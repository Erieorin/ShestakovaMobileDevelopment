package com.example.fragmentmanagerapp;

import android.os.Bundle;
import android.util.Log;
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

    public DetailsFragment() {
        super(R.layout.fragment_details);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        TextView textView = view.findViewById(R.id.textDetails);

        viewModel = new ViewModelProvider(requireActivity())
                .get(ShareViewModel.class);

        viewModel.getSelectedItem().observe(
                getViewLifecycleOwner(),
                item -> {
                    textView.setText("Вы выбрали: " + item);
                    Log.d("DetailsFragment", item);
                }
        );
    }
}