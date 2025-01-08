package com.example.tsa_softwaredev;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "UserPreferences";
    private static final String DIET_KEY = "DietType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkFirstLaunch();

        Button addActivityButton = findViewById(R.id.buttonAddActivity);
        addActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });
    }

    private void checkFirstLaunch() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (!preferences.contains(DIET_KEY)) {
            showDietSelectionDialog(preferences);
        }
    }

    private void showDietSelectionDialog(SharedPreferences preferences) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Your Diet Type");
        String[] diets = {"Vegan", "Mediterranean", "Paleo", "Keto", "Standard American"};
        builder.setSingleChoiceItems(diets, -1, (dialog, which) -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(DIET_KEY, diets[which]);
            editor.apply();
            dialog.dismiss();
        });
        builder.setCancelable(false);
        builder.show();
    }
}
