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

    private final float standardAmericanEmissions = 5.39775f;
    private final float mediterraneanEmissions = 2.17724f;
    private final float veganEmissions = 1.63293f;
    private final float paleoEmissions = 4.513245f;
    private final float ketoEmissions = 7.2801575f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        Button trackTransportation = findViewById(R.id.btn_track_transportation);
        Button trackEnergy = findViewById(R.id.btn_track_energy);
        Button saveButton = findViewById(R.id.btn_save);
        dietSpinner = findViewById(R.id.spinner_diet);



        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.diet_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dietSpinner.setAdapter(adapter);

        String savedDiet = prefs.getString("diet_type", "Standard American");
        int spinnerPosition = adapter.getPosition(savedDiet);
        dietSpinner.setSelection(spinnerPosition);

        dietSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDiet = parent.getItemAtPosition(position).toString();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("diet_type", selectedDiet);
                editor.apply();
                Toast.makeText(HomeActivity.this, "Diet saved: " + selectedDiet, Toast.LENGTH_SHORT).show();

                updateEmissionsBasedOnDiet(selectedDiet);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        trackTransportation.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, TransportationActivity.class));
        });

        trackEnergy.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, EnergyActivity.class));
        });

        saveButton.setOnClickListener(v -> {
            Toast.makeText(HomeActivity.this, "Preferences Saved!", Toast.LENGTH_SHORT).show();
        });

        updateEmissionsData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateEmissionsData();
    }

    private void addEnergyEmissions() {
        float energyEmissions = prefs.getFloat("energyEmissions", 0);
        totalEmissions += energyEmissions;  // Add the retrieved energy emissions to total emissions

        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("energyEmissions", energyEmissions);
        editor.apply();
    }

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

        addTransportationEmissions();

        updateEmissionsData();
    }

    private void addTransportationEmissions() {
        float transportEmissions = prefs.getFloat("transportationEmissions", 0);
        totalEmissions += transportEmissions;

        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("transportationEmissions", transportEmissions);
        editor.apply();
    }

    private void addDietEmissions() {
        String dietType = prefs.getString("diet_type", "Standard American");

        float dietEmissions = 0;
        switch (dietType) {
            case "Standard American":
                dietEmissions = standardAmericanEmissions;
                break;
            case "Mediterranean":
                dietEmissions = mediterraneanEmissions;
                break;
            case "Vegan":
                dietEmissions = veganEmissions;
                break;
            case "Paleo":
                dietEmissions = paleoEmissions;
                break;
            case "Keto":
                dietEmissions = ketoEmissions;
                break;
        }

        totalEmissions += dietEmissions;

        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("dietEmissions", dietEmissions);
        editor.apply();
    }

    private void updateEmissionsData() {
        addEnergyEmissions();
        addTransportationEmissions();
        addDietEmissions();

        float averageEmissions = totalEmissions / daysTracked;

        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("totalEmissions", totalEmissions);
        editor.apply();

    }
}
