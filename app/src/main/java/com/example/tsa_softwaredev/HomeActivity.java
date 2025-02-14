package com.example.tsa_softwaredev;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private float totalEmissions = 0;  
    private int daysTracked = 1;  

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
        Button selectDietButton = findViewById(R.id.btn_select_diet);

        trackTransportation.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, TransportationActivity.class));
        });

        trackEnergy.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, EnergyActivity.class));
        });

        selectDietButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, DietSelectionActivity.class);
            startActivity(intent);
        });

        updateEmissionsData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateEmissionsData();
    }

    private void updateEmissionsData() {
        addEnergyEmissions();
        addTransportationEmissions();
        addDietEmissions();

        
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("totalEmissions", totalEmissions);
    }

    private void applyBackgroundColor(int color) {
        
        View rootView = findViewById(android.R.id.content);
        rootView.setBackgroundColor(color);
    }

    private void addEnergyEmissions() {
        float energyEmissions = prefs.getFloat("energyEmissions", 0);
        totalEmissions += energyEmissions;
    }

    private void addTransportationEmissions() {
        float transportEmissions = prefs.getFloat("transportationEmissions", 0);
        totalEmissions += transportEmissions;
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
    }
}
