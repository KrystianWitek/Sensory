package com.example.krystian.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import java.util.Date;

/**
 * Created by Krystian on 05.07.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyBase3";
    public static final String TABLE_NAME = "locations_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "LONGITUDE";
    public static final String COL_3 = "LATITUDE";
    public static final String COL_4 = "CITY";
    public static final String COL_5 = "MY_DATE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

        SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "LONGITUDE DOUBLE, LATITUDE DOUBLE, CITY TEXT, MY_DATE TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(double LONGITUDE, double LATITUDE, String CITY, String MY_DATE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, LONGITUDE);
        contentValues.put(COL_3, LATITUDE);
        contentValues.put(COL_4, CITY);
        contentValues.put(COL_5, MY_DATE);

        long b = db.insert(TABLE_NAME,null,contentValues);
        if ( b == -1 ) return false;
        else return true;

        /*long result = db.insert(TABLE_NAME, null, contentValues);*/
        /*if(result == -1)
            return false;
        else*/
            //return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }
}
