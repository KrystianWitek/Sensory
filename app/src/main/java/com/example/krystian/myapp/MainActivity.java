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

    public void StartProximity(View v){
        Context context;
        context = getApplicationContext();
        Intent proximityIntent = new Intent(context, ProximityActivity.class);
        startActivity(proximityIntent);
    }

    public void StartAccelerometr(View v){
        Context context;
        context = getApplicationContext();
        Intent accelerometrIntent = new Intent(context, AccelometrActivity.class);
        startActivity(accelerometrIntent);
    }

    public void StartCompass(View v){
        Context context;
        context = getApplicationContext();
        Intent compassIntent = new Intent(context, CompassActivity.class);
        startActivity(compassIntent);
    }

    public void TestMaps(View v){
        Context context;
        context = getApplicationContext();
        Intent mapsIntent = new Intent(context, MapsActivity.class);
        startActivity(mapsIntent);
    }

}
