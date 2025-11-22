package com.example.rumireashestakovaculinarybook.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.rumireashestakovaculinarybook.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegister;

    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        authViewModel = new ViewModelProvider(
                this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(AuthViewModel.class);

        if (authViewModel.isUserLoggedIn().getValue() != null
                && authViewModel.isUserLoggedIn().getValue()) {
            startMainActivity();
        }

        authViewModel.getLoginSuccess().observe(this, success -> {
            if (Boolean.TRUE.equals(success)) startMainActivity();
        });
        authViewModel.getRegisterSuccess().observe(this, success -> {
            if (Boolean.TRUE.equals(success)) startMainActivity();
        });
        authViewModel.getErrorMessage().observe(this, msg -> {
            if (msg != null) Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            authViewModel.login(email, password);
        });

        btnRegister.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            authViewModel.register(email, password);
        });
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
