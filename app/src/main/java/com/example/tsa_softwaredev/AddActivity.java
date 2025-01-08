package com.example.tsa_softwaredev;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private Spinner activityTypeSpinner;
    private EditText distanceInput;
    private EditText caloriesInput;
    private static final String PREFS_NAME = "UserPreferences";
    private static final String DIET_KEY = "DietType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        activityTypeSpinner = findViewById(R.id.spinnerActivityType);
        distanceInput = findViewById(R.id.editTextDistance);
        caloriesInput = findViewById(R.id.editTextCalories);
        Button saveButton = findViewById(R.id.buttonSave);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityTypeSpinner.setAdapter(adapter);

        saveButton.setOnClickListener(v -> saveActivity());
    }

    private void saveActivity() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String diet = preferences.getString(DIET_KEY, "Vegan"); // Default to vegan if not found
        String activityType = activityTypeSpinner.getSelectedItem().toString();
        String distanceText = distanceInput.getText().toString();
        String caloriesText = caloriesInput.getText().toString();

        if (activityType.equals("Select Activity")) {
            Toast.makeText(this, "Please select a valid activity", Toast.LENGTH_SHORT).show();
            return;
        }

        if (distanceText.isEmpty() || caloriesText.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double distance = Double.parseDouble(distanceText);
        double calories = Double.parseDouble(caloriesText);
        double carbonFootprint = 0.0;

        // Diet-based carbon footprint calculation
        switch (diet.toLowerCase()) {
            case "vegan":
                carbonFootprint += 1.63293;
                break;
            case "mediterranean":
                carbonFootprint += 2.17724;
                break;
            case "paleo":
                carbonFootprint += 0.00196556666 * calories;
                break;
            case "keto":
                carbonFootprint += 0.00316528586 * calories;
                break;
            case "standard american":
                carbonFootprint += 0.00234684782 * calories;
                break;
        }

        Toast.makeText(this, "Carbon Footprint: " + carbonFootprint + " kg CO2", Toast.LENGTH_LONG).show();
        finish();
    }
}
