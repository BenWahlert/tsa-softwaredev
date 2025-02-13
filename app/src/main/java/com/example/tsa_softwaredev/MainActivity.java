package com.example.tsa_softwaredev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView statsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button homeButton = findViewById(R.id.home_button);
        Button tipsButton = findViewById(R.id.tips_button);
        statsTextView = findViewById(R.id.tv_stats);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });

        tipsButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                tips();
            }
        });

        updateEmissionsDisplay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateEmissionsDisplay();
    }

    public void home() {
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
    }

    public void tips() {
        startActivity(new Intent(MainActivity.this, TipsActivity.class));
    }

    public void updateEmissionsDisplay() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        float totalEmissions = sharedPreferences.getFloat("totalEmissions", 0.0f);
        statsTextView.setText(totalEmissions + "");
    }
}
