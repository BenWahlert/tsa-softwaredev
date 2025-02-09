package com.example.tsa_softwaredev;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Spinner dietSpinner;
    private SharedPreferences prefs;


    private float totalEmissions = 0;  // In kg
    private int daysTracked = 1;  // Assuming at least 1 day of tracking

    private TextView totalEmissionsTextView;
    private TextView averageEmissionsTextView;

    // Diet type emissions mapping (kg/day)
    private final float standardAmericanEmissions = 5.39775f;
    private final float mediterraneanEmissions = 2.17724f;
    private final float veganEmissions = 1.63293f;
    private final float paleoEmissions = 4.513245f;
    private final float ketoEmissions = 7.2801575f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize SharedPreferences
        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        // Find UI Elements
        Button trackTransportation = findViewById(R.id.btn_track_transportation);
        Button trackEnergy = findViewById(R.id.btn_track_energy);
        Button saveButton = findViewById(R.id.btn_save);
        dietSpinner = findViewById(R.id.spinner_diet);

        // Find the TextViews for emissions
        totalEmissionsTextView = findViewById(R.id.tv_total_emissions);
        averageEmissionsTextView = findViewById(R.id.tv_average_emissions);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Set up Diet Spinner (Dropdown)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.diet_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dietSpinner.setAdapter(adapter);

        // Load saved diet preference
        String savedDiet = prefs.getString("diet_type", "Standard American");
        int spinnerPosition = adapter.getPosition(savedDiet);
        dietSpinner.setSelection(spinnerPosition);

        // Handle diet selection
        dietSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDiet = parent.getItemAtPosition(position).toString();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("diet_type", selectedDiet);
                editor.apply();
                Toast.makeText(HomeActivity.this, "Diet saved: " + selectedDiet, Toast.LENGTH_SHORT).show();

                // Update emissions based on selected diet
                updateEmissionsBasedOnDiet(selectedDiet);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Button Click Listeners
        trackTransportation.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, TransportationActivity.class));
        });

        trackEnergy.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, EnergyActivity.class));
        });

        saveButton.setOnClickListener(v -> {
            Toast.makeText(HomeActivity.this, "Preferences Saved!", Toast.LENGTH_SHORT).show();
        });

        // Retrieve and update the total emissions from SharedPreferences
        updateEmissionsData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update emissions data every time the HomeActivity is accessed
        updateEmissionsData();
    }
    private void addEnergyEmissions() {
        // Retrieve the stored energy emissions from SharedPreferences
        float energyEmissions = prefs.getFloat("energyEmissions", 0);
        totalEmissions += energyEmissions;  // Add the retrieved energy emissions to total emissions
    }

    // Method to update emissions based on selected diet
    private void updateEmissionsBasedOnDiet(String diet) {
        switch (diet) {
            case "Standard American":
                totalEmissions = standardAmericanEmissions;
                break;
            case "Mediterranean":
                totalEmissions = mediterraneanEmissions;
                break;
            case "Vegan":
                totalEmissions = veganEmissions;
                break;
            case "Paleo":
                totalEmissions = paleoEmissions;
                break;
            case "Keto":
                totalEmissions = ketoEmissions;
                break;
            default:
                totalEmissions = 0;
                break;
        }

        // Add transportation emissions from SharedPreferences
        addTransportationEmissions();

        // Update the emissions data
        updateEmissionsData();
    }

    // Method to add transportation emissions from SharedPreferences
    private void addTransportationEmissions() {
        float transportEmissions = prefs.getFloat("transportationEmissions", 0);
        totalEmissions += transportEmissions;
    }


    private void updateEmissionsData() {
        addEnergyEmissions();
        addTransportationEmissions();

        float averageEmissions = totalEmissions / daysTracked;

        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("totalEmissions", totalEmissions);
        editor.apply();

        totalEmissionsTextView.setText("Total Carbon Emissions: " + totalEmissions + " kg");
        averageEmissionsTextView.setText("Average Carbon Emissions/Day: " + averageEmissions + " kg");
    }

}
