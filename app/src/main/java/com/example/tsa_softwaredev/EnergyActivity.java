package com.example.tsa_softwaredev;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnergyActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy);

        // Initialize SharedPreferences
        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        // Find UI elements
        EditText etEnergyUsage = findViewById(R.id.et_energy_usage); // EditText for energy consumption
        Spinner spinnerEnergySource = findViewById(R.id.spinner_energy_source); // Spinner for energy source selection
        Button btnStartEnergyTracking = findViewById(R.id.btn_start_energy_tracking); // Button to track energy data

        // Set up the Spinner with energy source options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.energy_sources, android.R.layout.simple_spinner_item); // Create an adapter from resources
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Set the dropdown view
        spinnerEnergySource.setAdapter(adapter); // Set the adapter to the spinner

        // Button click listener for tracking energy usage
        btnStartEnergyTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the energy usage input
                String energyUsage = etEnergyUsage.getText().toString();

                // Get the selected energy source
                String energySource = spinnerEnergySource.getSelectedItem().toString();

                if (energyUsage.isEmpty()) {
                    // Show a message if energy usage is not entered
                    Toast.makeText(EnergyActivity.this, "Please enter the energy usage", Toast.LENGTH_SHORT).show();
                } else {
                    // Calculate emissions based on the energy source and usage
                    float emissions = calculateEnergyEmissions(energySource, Float.parseFloat(energyUsage));

                    // Save the emissions data in SharedPreferences
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putFloat("energyEmissions", emissions);  // Store the calculated energy emissions
                    editor.apply();

                    // Show a toast with the calculated emissions
                    Toast.makeText(EnergyActivity.this, "Energy Emissions Tracked: " + emissions + " kg", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to calculate energy emissions based on the energy source
    private float calculateEnergyEmissions(String energySource, float energyUsage) {
        float emissionsFactor;

        // Example emission factors for different energy sources (in kg/kWh)
        switch (energySource) {
            case "Electricity (Coal)":
                emissionsFactor = 0.9f; // Example value: 0.9 kg/kWh for coal-based electricity
                break;
            case "Electricity (Solar)":
                emissionsFactor = 0.05f; // Example value: 0.05 kg/kWh for solar-based electricity
                break;
            case "Gas":
                emissionsFactor = 2.3f; // Example value: 2.3 kg/kWh for gas
                break;
            default:
                emissionsFactor = 0; // No emissions for unknown energy sources
                break;
        }

        // Return the emissions for the given energy source and usage
        return emissionsFactor * energyUsage;
    }
}
