package com.example.tsa_softwaredev;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerDiet, spinnerTransport;
    private EditText editTextDistance;
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

        // Check if the distance field is empty
        if (distanceText.isEmpty()) {
            editTextDistance.setError("Please enter a valid distance.");
            return; // Exit the method if no distance is entered
        }

        double distance = Double.parseDouble(distanceText);
        double carbonFootprint = 0.0;

        // Estimate carbon footprint based on diet
        if (diet.equals("vegan")) {
            carbonFootprint += 1.5;
        } else if (diet.equals("omnivore")) {
            carbonFootprint += 3.0;
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
        textViewResult.setText("Estimated Carbon Footprint: " + carbonFootprint + " tons/year");
    }
}
