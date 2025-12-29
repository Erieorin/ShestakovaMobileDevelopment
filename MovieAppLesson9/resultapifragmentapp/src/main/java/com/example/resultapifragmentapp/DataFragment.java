package com.example.resultapifragmentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class DataFragment extends Fragment {

    public DataFragment() {
        super(R.layout.fragment_data);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        Button button = view.findViewById(R.id.buttonOpenBottomSheet);

        button.setOnClickListener(click -> {
            String text = ((EditText) view.findViewById(R.id.editTextInfo)).getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString("key", text);

            getChildFragmentManager().setFragmentResult("requestKey", bundle);

            BottomSheetFragment bottomSheet = new BottomSheetFragment();
            bottomSheet.show(getChildFragmentManager(), "ModalBottomSheet");
        });

        return view;
    }
}