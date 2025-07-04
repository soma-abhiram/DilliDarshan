package com.kasinadhuni.tourismapp;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.kasinadhuni.tourismapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ViewBinding A little change
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Bottom navigation setup
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_attractions);

        // Top-level destinations (no back button for these)
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_attractions,
                R.id.navigation_map,
                R.id.navigation_reviews
        ).build();

        // Navigation controller
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        // ⬅️ Set up action bar with back button when not in top-level destinations
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // ⬇️ Set up BottomNavigationView with NavController
        NavigationUI.setupWithNavController(navView, navController);

        // Optional: Debug navigation changes
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            android.util.Log.d("Navigation", "Navigated to: " + destination.getLabel());
        });
    }

    // ⬅️ Ensure system back arrow works with NavController
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}