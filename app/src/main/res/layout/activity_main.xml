package com.example.tsa_softwaredev;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextDiet, editTextTransport;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDiet = findViewById(R.id.editTextDiet);
        editTextTransport = findViewById(R.id.editTextTransport);
        buttonCalculate = findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(v -> calculateCarbonFootprint());
    }

    private void calculateCarbonFootprint() {
        String diet = editTextDiet.getText().toString().toLowerCase();
        String transport = editTextTransport.getText().toString().toLowerCase();

        double carbonFootprint = 0.0;

        // Basic estimation logic
        if (diet.equals("vegan")) {
            carbonFootprint += 1.5;
        } else if (diet.equals("omnivore")) {
            carbonFootprint += 3.0;
        }

        if (transport.equals("car")) {
            carbonFootprint += 5.0;
        } else if (transport.equals("bus")) {
            carbonFootprint += 2.0;
        }

        // Display results (you can create a TextView for this)
        System.out.println("Estimated Carbon Footprint: " + carbonFootprint + " tons/year");
    }
}
