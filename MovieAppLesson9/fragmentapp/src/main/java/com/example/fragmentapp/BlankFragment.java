package com.example.fragmentapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BlankFragment extends Fragment {

    public static BlankFragment newInstance(int numberStudent) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt("my_number_student", numberStudent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int numberStudent = requireArguments().getInt("my_number_student", 22);
        Log.d(BlankFragment.class.getSimpleName(), "Number: " + numberStudent);
    }
}

