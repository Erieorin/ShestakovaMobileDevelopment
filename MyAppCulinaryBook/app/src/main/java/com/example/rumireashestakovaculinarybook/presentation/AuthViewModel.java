package com.example.rumireashestakovaculinarybook.presentation;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.data.storage.SharedPrefStorage;
import com.example.domain.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends AndroidViewModel {

    private final FirebaseAuth auth;
    private final SharedPrefStorage sharedPrefs;

    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    private final MutableLiveData<Boolean> registerSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isUserLoggedIn = new MutableLiveData<>();
    private final MutableLiveData<String> currentUserEmail = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
        Context context = application.getApplicationContext();
        this.auth = FirebaseAuth.getInstance();
        this.sharedPrefs = new SharedPrefStorage(context);

        String email = sharedPrefs.getUser();
        currentUserEmail.setValue(email);
        isUserLoggedIn.setValue(email != null);
    }

    public void login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage.setValue("Введите email и пароль");
            return;
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        sharedPrefs.saveUser(email);
                        loginSuccess.setValue(true);
                        isUserLoggedIn.setValue(true);
                        currentUserEmail.setValue(email);
                    } else {
                        errorMessage.setValue("Ошибка входа: " + task.getException().getMessage());
                    }
                });
    }

    public void register(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage.setValue("Введите email и пароль");
            return;
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        sharedPrefs.saveUser(email);
                        registerSuccess.setValue(true);
                        isUserLoggedIn.setValue(true);
                        currentUserEmail.setValue(email);
                    } else {
                        errorMessage.setValue("Ошибка регистрации: " + task.getException().getMessage());
                    }
                });
    }

    public void logout() {
        auth.signOut();
        sharedPrefs.clear();
        isUserLoggedIn.setValue(false);
        currentUserEmail.setValue(null);
    }

    public LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }

    public LiveData<Boolean> getRegisterSuccess() {
        return registerSuccess;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> isUserLoggedIn() {
        return isUserLoggedIn;
    }

    public LiveData<String> getCurrentUserEmail() {
        return currentUserEmail;
    }
}