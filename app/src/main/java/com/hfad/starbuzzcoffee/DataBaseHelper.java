package com.hfad.starbuzzcoffee;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 1;

    DataBaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE DRINK("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER);");

        insertDrink(sqLiteDatabase, "Latte", "Espresso and steamed milk", R.drawable.latte);
        insertDrink(sqLiteDatabase, "Cappuccino", "Espresso, hot milk and steamed-milk foam", R.drawable.cappuccino);
        insertDrink(sqLiteDatabase, "Filter", "Our best drip coffee", R.drawable.filter);

    }

    private void insertDrink(SQLiteDatabase sqLiteDatabase, String name, String description, int resourceId) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        sqLiteDatabase.insert("DRINK", null, drinkValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
