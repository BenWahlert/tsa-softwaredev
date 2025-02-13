package com.example.tsa_softwaredev;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TipsActivity extends AppCompatActivity {

    List<String> energyIntroMessages = Arrays.asList(
            "Your energy usage is on the higher side.",
            "You might want to consider reducing your energy consumption.",
            "Your energy consumption could be optimized to reduce emissions."
    );

    List<String> dietIntroMessages = Arrays.asList(
            "Your diet contributes a bit more to emissions.",
            "Consider modifying your diet to lower your carbon footprint.",
            "Your food choices are impacting the environment more than expected."
    );

    List<String> transportationIntroMessages = Arrays.asList(
            "Your transportation footprint is significant.",
            "Your transportation habits are contributing to your emissions.",
            "You might want to consider greener transportation options."
    );

    List<String> generalIntroMessages = Arrays.asList(
            "Your overall emissions could be lower.",
            "Consider making small changes to reduce your carbon footprint.",
            "You're contributing more to emissions than you'd like to."
    );

    List<String> energyTips = Arrays.asList(
            "Unplug devices when they're not in use to avoid phantom energy drain.",
            "Switch to energy-efficient LED bulbs to save electricity.",
            "Use power strips and turn them off when not needed.",
            "Wash clothes in cold water and air dry them whenever possible.",
            "Set your thermostat a few degrees lower in winter and higher in summer.",
            "Upgrade to energy-efficient appliances to cut down on electricity use.",
            "Turn off lights when leaving a room to reduce unnecessary power usage.",
            "Consider installing solar panels to generate clean energy.",
            "Use blackout curtains to naturally regulate indoor temperatures.",
            "Limit the use of space heaters and rely on layered clothing instead."
    );

    List<String> dietTips = Arrays.asList(
            "Try reducing meat consumption—start with Meatless Mondays!",
            "Choose locally sourced produce to cut down on transportation emissions.",
            "Avoid food waste by planning meals and using leftovers creatively.",
            "Eat more plant-based meals to lower your carbon footprint.",
            "Buy in bulk and use reusable containers to reduce packaging waste.",
            "Compost food scraps instead of sending them to landfills.",
            "Support sustainable farming by buying organic when possible.",
            "Grow your own herbs or vegetables to reduce transport emissions.",
            "Drink tap water instead of bottled water to cut down on plastic waste.",
            "Reduce dairy consumption—plant-based alternatives can be just as tasty!"
    );

    List<String> transportationTips = Arrays.asList(
            "Walk or bike for short trips instead of driving.",
            "Use public transportation whenever possible to reduce emissions.",
            "Carpool with friends or coworkers to cut down on fuel use.",
            "Switch to an electric or hybrid vehicle if you're considering a new car.",
            "Plan errands efficiently to reduce unnecessary trips.",
            "Drive smoothly—avoid rapid acceleration and braking to save fuel.",
            "Keep your tires properly inflated to improve fuel efficiency.",
            "Turn off your engine instead of idling to save gas and reduce pollution.",
            "Consider working from home or telecommuting to cut down on travel.",
            "Take trains instead of planes for shorter trips to lower emissions."
    );

    List<String> generalTips = Arrays.asList(
            "Support businesses that prioritize sustainability and eco-friendly practices.",
            "Get involved in local environmental initiatives and clean-ups.",
            "Use reusable shopping bags, coffee cups, and utensils to cut down on waste.",
            "Advocate for climate policies that support renewable energy.",
            "Switch to eco-friendly cleaning products to reduce harmful chemicals.",
            "Reduce plastic use by avoiding disposable packaging.",
            "Repair and repurpose items instead of throwing them away.",
            "Turn off electronics at night to conserve power.",
            "Educate friends and family about sustainable living practices.",
            "Take shorter showers to reduce water and energy consumption."
    );

    private TextView tipOfTheDayTextView;
    private Button refreshButton;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        tipOfTheDayTextView = findViewById(R.id.tip_of_the_day_text_view);
        refreshButton = findViewById(R.id.refresh_button);
        homeButton = findViewById(R.id.home_button);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        float energyEmissions = prefs.getFloat("energyEmissions", 0);
        float dietEmissions = prefs.getFloat("dietEmissions", 0);
        float transportationEmissions = prefs.getFloat("transportationEmissions", 0);
        float totalEmissions = energyEmissions + dietEmissions + transportationEmissions;

        displayTipOfTheDay(energyEmissions, dietEmissions, transportationEmissions, totalEmissions);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayTipOfTheDay(energyEmissions, dietEmissions, transportationEmissions, totalEmissions);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayTipOfTheDay(float energy, float diet, float transportation, float total) {
        String tip = getTipBasedOnEmissions(energy, diet, transportation, total);
        tipOfTheDayTextView.setText(tip);
    }

    private String getTipBasedOnEmissions(float energy, float diet, float transportation, float total) {
        String tipMessage = "";

        if (energy > 10) {
            tipMessage = getRandomIntroMessage(energyIntroMessages) + " " + getRandomTip(energyTips);
        } else if (diet > 10) {
            tipMessage = getRandomIntroMessage(dietIntroMessages) + " " + getRandomTip(dietTips);
        } else if (transportation > 10) {
            tipMessage = getRandomIntroMessage(transportationIntroMessages) + " " + getRandomTip(transportationTips);
        } else if (total > 30) {
            tipMessage = getRandomIntroMessage(generalIntroMessages) + " " + getRandomTip(generalTips);
        } else {
            tipMessage = "You're doing great at reducing your carbon footprint! Keep up the good work.";
        }

        return tipMessage;
    }

    private String getRandomIntroMessage(List<String> introMessages) {
        return introMessages.get(new Random().nextInt(introMessages.size()));
    }

    private String getRandomTip(List<String> tipList) {
        return tipList.get(new Random().nextInt(tipList.size()));
    }
}
