package com.example.tsa_softwaredev;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DietSelectionActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private RadioGroup rgDiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_selection);

        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        rgDiet = findViewById(R.id.rg_diet);

        
        Button saveButton = findViewById(R.id.btn_save);
        Button cancelButton = findViewById(R.id.btn_cancel);

        
        saveButton.setOnClickListener(v -> saveDietSelection());
        cancelButton.setOnClickListener(v -> finish());

        
        loadDietSelection();
    }

    private void loadDietSelection() {
        String savedDiet = prefs.getString("diet_type", null);

        if (savedDiet != null) {
            
            switch (savedDiet) {
                case "Standard American":
                    rgDiet.check(R.id.rb_standard_american);
                    break;
                case "Mediterranean":
                    rgDiet.check(R.id.rb_mediterranean);
                    break;
                case "Vegan":
                    rgDiet.check(R.id.rb_vegan);
                    break;
                case "Paleo":
                    rgDiet.check(R.id.rb_paleo);
                    break;
                case "Keto":
                    rgDiet.check(R.id.rb_keto);
                    break;
            }
        }
    }

    private void saveDietSelection() {
        
        int selectedId = rgDiet.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(DietSelectionActivity.this, "Please select a diet", Toast.LENGTH_SHORT).show();
            return;
        }

        
        RadioButton selectedRadioButton = findViewById(selectedId);
        String diet = selectedRadioButton.getText().toString();

        
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("diet_type", diet);
        editor.apply();

        
        Toast.makeText(DietSelectionActivity.this, "Diet saved: " + diet, Toast.LENGTH_SHORT).show();
        finish();
    }
}
