package com.example.tsa_softwaredev;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class EnergyActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy);

        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        EditText etEnergyUsage = findViewById(R.id.et_energy_usage);
        RadioGroup rgEnergySource = findViewById(R.id.rg_energy_source);
        MaterialButton btnStartEnergyTracking = findViewById(R.id.btn_start_energy_tracking);
        MaterialButton btnCancel = findViewById(R.id.btn_cancel); // Cancel button

        btnStartEnergyTracking.setOnClickListener(v -> {
            int selectedId = rgEnergySource.getCheckedRadioButtonId();
            String energyUsage = etEnergyUsage.getText().toString();

            if (selectedId == -1) {
                Toast.makeText(EnergyActivity.this, "Please select an energy source", Toast.LENGTH_SHORT).show();
                return;
            }

            if (energyUsage.isEmpty()) {
                Toast.makeText(EnergyActivity.this, "Please enter the energy usage", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadioButton = findViewById(selectedId);
            String energySource = selectedRadioButton.getText().toString();

            float emissions = calculateEnergyEmissions(energySource, Float.parseFloat(energyUsage));

            prefs.edit().putFloat("energyEmissions", emissions).apply();

            Toast.makeText(EnergyActivity.this,
                    "Energy Source: " + energySource + "\nEmissions Tracked: " + emissions + " kg CO₂",
                    Toast.LENGTH_LONG).show();
        });

        // Handle Cancel button click
        btnCancel.setOnClickListener(v -> finish()); // Close the activity when cancel is clicked
    }

    private float calculateEnergyEmissions(String energySource, float energyUsage) {
        float emissionsFactor;
        switch (energySource) {
            case "Electricity (Coal)":
                emissionsFactor = 0.9f;
                break;
            case "Electricity (Solar)":
                emissionsFactor = 0.05f;
                break;
            case "Gas":
                emissionsFactor = 2.3f;
                break;
            default:
                emissionsFactor = 0;
                break;
        }
        return emissionsFactor * energyUsage;
    }
}
