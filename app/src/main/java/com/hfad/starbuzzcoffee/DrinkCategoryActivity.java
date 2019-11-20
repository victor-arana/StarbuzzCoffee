package com.hfad.starbuzzcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DrinkCategoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

        ListView drinks = findViewById(R.id.list_drinks);

        drinks.setOnItemClickListener(this);

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDataBaseHelper(this);
        try {
            db = starbuzzDatabaseHelper.getReadableDatabase();

            // Create a cursor
            String table = "DRINK";
            String[] columns = new String[]{"_id", "NAME"};

            cursor =
                    db.query(table, columns, null, null, null, null, null);

            // Create adapter
            Context context = this;
            int layout = android.R.layout.simple_list_item_1;
            String[] fromColumns = new String[]{"NAME"};
            int[] toViews = new int[]{android.R.id.text1};
            int flags = 0; // Used to determine the behavior of the cursor

            SimpleCursorAdapter adapter =
                    new SimpleCursorAdapter(context, layout, cursor, fromColumns, toViews, flags);

            drinks.setAdapter(adapter);

            } catch (SQLiteException e){
                Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        // Pass the drink the user clicks on to DrinkActivity
        Intent intent = new Intent(this, DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINK_ID, (int) id);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
