package com.example.tsa_softwaredev;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TransportationActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation);

        // Initialize SharedPreferences
        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        // Find UI elements
        EditText etDistance = findViewById(R.id.et_distance);
        Spinner spinnerTransportMode = findViewById(R.id.spinner_transport_mode);
        Button btnStartTracking = findViewById(R.id.btn_start_tracking);

        // Button click listener
        btnStartTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the distance input
                String distance = etDistance.getText().toString();

                // Get the selected transport mode
                String transportMode = spinnerTransportMode.getSelectedItem().toString();

                if (distance.isEmpty()) {
                    // Show a message if distance is not entered
                    Toast.makeText(TransportationActivity.this, "Please enter the distance traveled", Toast.LENGTH_SHORT).show();
                } else {
                    // Calculate emissions based on the transport mode
                    float emissions = calculateEmissions(transportMode, Float.parseFloat(distance));

                    // Save the emissions data in SharedPreferences
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putFloat("transportationEmissions", emissions);
                    editor.apply();

                    // Show a toast with the calculated emissions
                    Toast.makeText(TransportationActivity.this, "Emissions Tracked: " + emissions + " kg", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to calculate emissions based on the transport mode
    private float calculateEmissions(String transportMode, float distance) {
        float emissionsPerKm;

        switch (transportMode) {
            case "Car":
                emissionsPerKm = 0.25f; // Example: 0.25 kg/km for car
                break;
            case "Bus":
                emissionsPerKm = 0.05f; // Example: 0.05 kg/km for bus
                break;
            case "Bike":
                emissionsPerKm = 0.01f; // Example: 0.01 kg/km for bike
                break;
            default:
                emissionsPerKm = 0;
                break;
        }

        return emissionsPerKm * distance;
    }
}
