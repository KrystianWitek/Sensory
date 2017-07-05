package com.example.krystian.myapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.pm.ActivityInfo;

public class MainActivity extends AppCompatActivity {

    public Intent gpsIntent;
    public Intent dataBaseIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void StartGPS(View v){
        Context context;
        context = getApplicationContext();
        Intent gpsIntent = new Intent(context, GetCurrentLocation.class);
        startActivity(gpsIntent);
    }

    public void StartDataBase(View v){
        Context context;
        context = getApplicationContext();
        Intent dataBaseIntent = new Intent(context, GetCurrentLocation.class);
        startActivity(dataBaseIntent);
    }

}
