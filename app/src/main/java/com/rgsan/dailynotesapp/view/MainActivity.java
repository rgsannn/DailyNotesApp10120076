package com.rgsan.dailynotesapp.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.rgsan.dailynotesapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        final ProfileFragment profileFragment = new ProfileFragment();
        final DailyNotesFragment dailyNotesFragment = new DailyNotesFragment();
        final AppInfoFragment appInfoFragment = new AppInfoFragment();

        loadFragment(profileFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_profile:
                    loadFragment(profileFragment);
                    return true;
                case R.id.menu_daily_notes:
                    loadFragment(dailyNotesFragment);
                    return true;
                case R.id.menu_app_info:
                    loadFragment(appInfoFragment);
                    return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }
}