package com.hfad.starbuzzcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK_ID = "drinkId";

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        // Get the drink from the intent
        int drinkId = getIntent().getIntExtra(EXTRA_DRINK_ID, 0);

        SQLiteOpenHelper databaseHelper = new StarbuzzDataBaseHelper(this);
        try {
            db = databaseHelper.getReadableDatabase();

            // Create cursor
            String table = "DRINK";
            String[] columns = new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"};
            String selection = "_id = ?";
            String[] selectionArgs = new String[] {Integer.toString(drinkId)};

            cursor =
                    db.query(table, columns, selection, selectionArgs, null, null, null);

            // Move to the  first  record on the  cursor
            if( cursor.moveToFirst() ){
                String name =  cursor.getString(0);
                String description  =  cursor.getString(1);
                int photoId =  cursor.getInt(2 );

                // Populate the drink name
                TextView nameView = findViewById(R.id.name);
                nameView.setText(name);

                // Populate the drink description
                TextView descriptionView = findViewById(R.id.description);
                descriptionView.setText(description);

                // Populate the drink image
                ImageView photo = findViewById(R.id.photo);
                photo.setImageResource(photoId);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
