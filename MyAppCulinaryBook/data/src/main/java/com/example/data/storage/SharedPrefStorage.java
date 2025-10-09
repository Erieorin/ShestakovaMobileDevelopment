package com.example.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefStorage {
    private static final String PREFS_NAME = "user_prefs";
    private static final String KEY_USERNAME = "username";
    private final SharedPreferences prefs;

    public SharedPrefStorage(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveUser(String username) {
        prefs.edit().putString(KEY_USERNAME, username).apply();
    }

    public String getUser() {
        return prefs.getString(KEY_USERNAME, null);
    }

    public void clear() {
        prefs.edit().clear().apply();
    }
}

