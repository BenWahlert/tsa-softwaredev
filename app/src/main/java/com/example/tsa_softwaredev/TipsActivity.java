package com.example.tsa_softwaredev;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;

public class TipsActivity extends AppCompatActivity {

    List<String> tips = Arrays.asList(
            "1. Switch to renewable energy sources like solar and wind.",
            "2. Reduce, reuse, and recycle to minimize waste.",
            "3. Use public transport, bike, or walk instead of driving.",
            "4. Plant trees to absorb CO2 and improve air quality.",
            "5. Consume less meat and dairy to reduce methane emissions.",
            "6. Support and buy from sustainable businesses.",
            "7. Turn off lights and electronics when not in use.",
            "8. Insulate your home to reduce heating and cooling needs.",
            "9. Use energy-efficient appliances and LED bulbs.",
            "10. Avoid single-use plastics and opt for reusable products.",
            "11. Collect rainwater for gardening and reduce water waste.",
            "12. Drive fuel-efficient or electric vehicles.",
            "13. Participate in local environmental initiatives.",
            "14. Eat seasonal and locally produced food.",
            "15. Unplug devices when not in use to save energy.",
            "16. Advocate for policies that support climate action.",
            "17. Reduce fast fashion consumption and buy sustainable clothing.",
            "18. Support companies committed to reducing carbon emissions.",
            "19. Use a programmable thermostat to optimize energy use.",
            "20. Take shorter showers to conserve water.",
            "21. Avoid food waste by planning meals and composting leftovers.",
            "22. Carpool with others to reduce carbon emissions.",
            "23. Reduce paper use by going digital.",
            "24. Install solar panels to generate clean energy.",
            "25. Use refillable water bottles instead of bottled water.",
            "26. Participate in tree-planting initiatives.",
            "27. Support green energy policies and investments.",
            "28. Reduce air travel and opt for video conferencing.",
            "29. Grow your own food to cut down on transportation emissions.",
            "30. Buy energy from suppliers that invest in renewables.",
            "31. Use biodegradable and non-toxic cleaning products.",
            "32. Reduce dependency on fossil fuels by conserving energy.",
            "33. Repair and repurpose items instead of discarding them.",
            "34. Reduce the use of chemical fertilizers and pesticides.",
            "35. Use hand dryers instead of paper towels.",
            "36. Volunteer for environmental conservation projects.",
            "37. Promote environmental education and awareness.",
            "38. Reduce packaging waste by buying in bulk.",
            "39. Use electric lawn mowers instead of gas-powered ones.",
            "40. Choose eco-friendly travel and tourism options.",
            "41. Encourage businesses to adopt sustainable practices.",
            "42. Avoid idling your car to save fuel and reduce emissions.",
            "43. Insulate water heaters to improve energy efficiency.",
            "44. Reduce synthetic clothing purchases to limit microplastics.",
            "45. Turn off air conditioning when not necessary.",
            "46. Avoid excessive packaging by choosing sustainable brands.",
            "47. Participate in beach and park clean-up activities.",
            "48. Use natural light whenever possible to reduce energy use.",
            "49. Avoid buying products with palm oil to prevent deforestation.",
            "50. Spread awareness and encourage others to take climate action."
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        Button tipsButton = findViewById(R.id.tips_button);

        tipsButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                Toast.makeText(TipsActivity.this, tips.get(new Random().nextInt(tips.size())), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
