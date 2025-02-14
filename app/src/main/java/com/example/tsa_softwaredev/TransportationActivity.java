package com.example.tsa_softwaredev;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TransportationActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation);

        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        EditText etDistance = findViewById(R.id.et_distance);
        RadioGroup rgTransportMode = findViewById(R.id.rg_transport_mode);
        Button btnStartTracking = findViewById(R.id.btn_start_tracking);
        Button btnCancel = findViewById(R.id.btn_cancel); 

        btnStartTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String distanceText = etDistance.getText().toString();

                if (distanceText.isEmpty()) {
                    Toast.makeText(TransportationActivity.this, "Please enter the distance traveled", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedId = rgTransportMode.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(TransportationActivity.this, "Please select a transportation mode", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedRadioButton = findViewById(selectedId);
                String transportMode = selectedRadioButton.getText().toString();

                float emissions = calculateEmissions(transportMode, Float.parseFloat(distanceText));

                SharedPreferences.Editor editor = prefs.edit();
                editor.putFloat("transportationEmissions", emissions);
                editor.apply();

                Toast.makeText(TransportationActivity.this, "Emissions Tracked: " + emissions + " kg", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); 
            }
        });
    }

    private float calculateEmissions(String transportMode, float distance) {
        float emissionsPerKm;

        switch (transportMode) {
            case "Car":
                emissionsPerKm = 0.25f;
                break;
            case "Bus":
                emissionsPerKm = 0.05f;
                break;
            case "Bike":
                emissionsPerKm = 0.01f;
                break;
            default:
                emissionsPerKm = 0;
                break;
        }

        return emissionsPerKm * distance;
    }
}
