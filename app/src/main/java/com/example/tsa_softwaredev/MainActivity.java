package com.example.tsa_softwaredev;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call the home method when the activity is created
        home();
    }

    public void home() {
        // Start HomeActivity and finish the current activity
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        finish();
    }
}
