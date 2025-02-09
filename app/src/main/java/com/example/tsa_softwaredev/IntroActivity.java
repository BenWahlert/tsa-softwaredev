package com.example.tsa_softwaredev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class IntroActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private IntroAdapter introAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Initialize ViewPager2 and set the adapter
        viewPager = findViewById(R.id.viewPager);
        introAdapter = new IntroAdapter(this);
        viewPager.setAdapter(introAdapter);

        // Set an OnPageChangeCallback to detect when the user reaches the last page (4th page)
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // If the 4th screen (empty screen) is selected, transition to the home screen (MainActivity)
                if (position == introAdapter.getItemCount()-1) {
                    // Immediately transition to MainActivity
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Finish the IntroActivity so it's removed from the stack
                }
            }
        });
    }
}
