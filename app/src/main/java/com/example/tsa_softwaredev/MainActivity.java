package com.example.tsa_softwaredev;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerDiet, spinnerTransport;
    private EditText editTextDistance;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerDiet = findViewById(R.id.spinnerDiet);
        spinnerTransport = findViewById(R.id.spinnerTransport);
        editTextDistance = findViewById(R.id.editTextDistance);
        buttonCalculate = findViewById(R.id.buttonCalculate);

        // Setup the diet spinner
        ArrayAdapter<CharSequence> dietAdapter = ArrayAdapter.createFromResource(this, 
                R.array.diet_options, android.R.layout.simple_spinner_item);
        dietAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDiet.setAdapter(dietAdapter);

        // Setup the transport spinner
        ArrayAdapter<CharSequence> transportAdapter = ArrayAdapter.createFromResource(this, 
                R.array.transport_options, android.R.layout.simple_spinner_item);
        transportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTransport.setAdapter(transportAdapter);

        buttonCalculate.setOnClickListener(v -> calculateCarbonFootprint());
    }

    private void calculateCarbonFootprint() {
        String diet = spinnerDiet.getSelectedItem().toString().toLowerCase();
        String transport = spinnerTransport.getSelectedItem().toString().toLowerCase();
        double distance = Double.parseDouble(editTextDistance.getText().toString());

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
        }

        // Display results (you can create a TextView for this)
        System.out.println("Estimated Carbon Footprint: " + carbonFootprint + " tons/year");
    }
}
