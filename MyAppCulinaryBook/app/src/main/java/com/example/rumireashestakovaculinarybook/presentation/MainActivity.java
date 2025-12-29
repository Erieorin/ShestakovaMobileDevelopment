package com.example.rumireashestakovaculinarybook.presentation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.rumireashestakovaculinarybook.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        if (savedInstanceState == null) {
            loadFragment(new RecipesFragment());
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            if (item.getItemId() == R.id.nav_recipes) fragment = new RecipesFragment();
            if (item.getItemId() == R.id.nav_profile) fragment = new ProfileFragment();
            if (item.getItemId() == R.id.nav_food) fragment = new FoodRecognitionFragment();
            if (fragment != null) loadFragment(fragment);
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null) // добавляем в BackStack
                .commit();
    }
}
