package com.example.tsa_softwaredev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private Spinner dietSpinner;
    private SharedPreferences prefs;

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

        // Set up Diet Spinner (Dropdown)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.diet_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dietSpinner.setAdapter(adapter);

        // Load saved diet preference
        String savedDiet = prefs.getString("diet_type", "Omnivore");
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
    }
}
