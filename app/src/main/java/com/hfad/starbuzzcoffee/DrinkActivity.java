package com.hfad.starbuzzcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK_ID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        // Get the drink from the intent
        int drinkId = getIntent().getIntExtra(EXTRA_DRINK_ID, 0);
        Drink drink = Drink.drinks[drinkId];

        // Populate the drink name
        TextView name = findViewById(R.id.name);
        name.setText(drink.getName());

        // Populate the drink description
        TextView description = findViewById(R.id.description);
        description.setText(drink.getDescription());

        // Populate the drink image

    }
}
