package com.example.krystian.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Krystian on 05.07.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyBase.db";
    public static final String TABLE_NAME = "locations_table.db";
    public static final String COL_1 = "ID.db";
    public static final String COL_2 = "LONGITUDE.db";
    public static final String COL_3 = "LATITUDE.db";
    public static final String COL_4 = "CITY.db";
    public static final String COL_5 = "DATE.db";


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
