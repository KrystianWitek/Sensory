package com.example.krystian.myapp;

import android.content.Context;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.location.*;

/**
 * Created by Krystian on 27.06.2017.
 */

public class GpsActivity extends AppCompatActivity{

    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
   // LocationListener locationListener = new MyLocationListener();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_location);
    }
}
