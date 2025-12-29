package com.example.rumireashestakovaculinarybook.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rumireashestakovaculinarybook.R;

public class ProfileFragment extends Fragment {

    private AuthViewModel authViewModel;
    private TextView tvEmail;
    private Button btnLogout;

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvEmail = view.findViewById(R.id.tvProfileEmail);
        btnLogout = view.findViewById(R.id.btnLogout);

        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        authViewModel.getCurrentUserEmail()
                .observe(getViewLifecycleOwner(), email ->
                        tvEmail.setText(email != null
                                ? "Вы вошли как:\n" + email
                                : "Пользователь не найден")
                );

        btnLogout.setOnClickListener(v -> {
            authViewModel.logout();
            startActivity(new Intent(requireContext(), AuthActivity.class));
            requireActivity().finish();
        });
    }
}
