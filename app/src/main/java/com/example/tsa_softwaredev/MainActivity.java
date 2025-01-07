package com.example.tsa_softwaredev;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerDiet, spinnerTransport;
    private EditText editTextDistance, editTextCalories;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        spinnerDiet = findViewById(R.id.spinnerDiet);
        spinnerTransport = findViewById(R.id.spinnerTransport);
        editTextDistance = findViewById(R.id.editTextDistance);
        editTextCalories = findViewById(R.id.editTextCalories);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        // Set up diet spinner
        ArrayAdapter<CharSequence> dietAdapter = ArrayAdapter.createFromResource(this, 
                R.array.diet_options, android.R.layout.simple_spinner_item);
        dietAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDiet.setAdapter(dietAdapter);

        // Set up transport spinner
        ArrayAdapter<CharSequence> transportAdapter = ArrayAdapter.createFromResource(this, 
                R.array.transport_options, android.R.layout.simple_spinner_item);
        transportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTransport.setAdapter(transportAdapter);

        // Set up button click listener
        buttonCalculate.setOnClickListener(v -> calculateCarbonFootprint());
    }

    private void calculateCarbonFootprint() {
        String diet = spinnerDiet.getSelectedItem().toString().toLowerCase();
        String transport = spinnerTransport.getSelectedItem().toString().toLowerCase();
        String distanceText = editTextDistance.getText().toString();
        String caloriesText = editTextCalories.getText().toString();

        if (diet.equals("select diet")) {
            Toast.makeText(this, "Please select a diet", Toast.LENGTH_SHORT).show();
            return;
        }
        if (transport.equals("select transport")) {
            Toast.makeText(this, "Please select a transport method", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the distance or calories fields are empty
        if (distanceText.isEmpty() || caloriesText.isEmpty()) {
            if (distanceText.isEmpty()) {
                editTextDistance.setError("Please enter a valid distance.");
            }
            if (caloriesText.isEmpty()) {
                editTextCalories.setError("Please enter calories consumed.");
            }
            return; // Exit the method if any field is empty
        }

        double distance = Double.parseDouble(distanceText);
        double calories = Double.parseDouble(caloriesText);
        double carbonFootprint = 0.0;

        // Estimate carbon footprint based on diet (average carbon cost per calorie)

        if (diet.equals("vegan")) {
            carbonFootprint += 1.63293;
        } else if (diet.equals("mediterranean")) {
            carbonFootprint += 2.17724;
        } else if (diet.equals("paleo")) {
            carbonFootprint += .00196556666 * calories;
        } else if (diet.equals("keto")) {
            carbonFootprint += 0.00316528586 * calories;
        } else if (diet.equals("standard american")) {
            carbonFootprint += 0.00234684782 * calories;
        }

        // Estimate carbon footprint based on transport method and distance
        if (transport.equals("car")) {
            carbonFootprint += (distance * 0.2); // Example: 0.2 kg CO2 per km
        } else if (transport.equals("bus")) {
            carbonFootprint += (distance * 0.05); // Example: 0.05 kg CO2 per km
        } else if (transport.equals("bike")) {
            carbonFootprint += (distance * 0.01); // Example: 0.01 kg CO2 per km
        } else if (transport.equals("walk")) {
            carbonFootprint += (distance * 0.0); // Walking has no carbon footprint
        }

        // Display the result in the TextView
        textViewResult.setText("Estimated Carbon Footprint: " + carbonFootprint + " kg CO2/day");
    }
}
