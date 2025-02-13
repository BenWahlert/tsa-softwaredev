package com.example.tsa_softwaredev;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DietSelectionActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private RadioGroup rgDiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_selection);

        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        rgDiet = findViewById(R.id.rg_diet);

        // Initialize buttons
        Button saveButton = findViewById(R.id.btn_save);
        Button cancelButton = findViewById(R.id.btn_cancel);

        // Set onClick listeners for buttons
        saveButton.setOnClickListener(v -> saveDietSelection());
        cancelButton.setOnClickListener(v -> finish());

        // Load saved diet type into the RadioGroup
        loadDietSelection();
    }

    private void loadDietSelection() {
        String savedDiet = prefs.getString("diet_type", null);

        if (savedDiet != null) {
            // If a saved diet type exists, check the corresponding RadioButton
            switch (savedDiet) {
                case "Standard American":
                    rgDiet.check(R.id.rb_standard_american);
                    break;
                case "Mediterranean":
                    rgDiet.check(R.id.rb_mediterranean);
                    break;
                case "Vegan":
                    rgDiet.check(R.id.rb_vegan);
                    break;
                case "Paleo":
                    rgDiet.check(R.id.rb_paleo);
                    break;
                case "Keto":
                    rgDiet.check(R.id.rb_keto);
                    break;
            }
        }
    }

    private void saveDietSelection() {
        // Check if a diet is selected
        int selectedId = rgDiet.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(DietSelectionActivity.this, "Please select a diet", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the selected diet type
        RadioButton selectedRadioButton = findViewById(selectedId);
        String diet = selectedRadioButton.getText().toString();

        // Save the selected diet to SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("diet_type", diet);
        editor.apply();

        // Show confirmation Toast and close the activity
        Toast.makeText(DietSelectionActivity.this, "Diet saved: " + diet, Toast.LENGTH_SHORT).show();
        finish();
    }
}
