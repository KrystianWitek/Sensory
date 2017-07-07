package com.example.krystian.myapp;

import android.app.Activity;
import android.app.Service;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Krystian on 06.07.2017.
 */

public class ProximityActivity extends Activity implements SensorEventListener{

    TextView textView;
    SensorManager sensorManager;
    Sensor sensor;
    private static final int SENSOR_SENSITIVITY = 4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proximity_activity);

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(sensor == null) {
            Toast.makeText(getBaseContext(),"Proximity nie dziala",Toast.LENGTH_SHORT).show();
            finish();
        }

        textView = (TextView) findViewById(R.id.proximity_text);

        sensorManager.registerListener(this, sensor, 90 * 1000 * 1000);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
    }

    public void onSensorChanged(SensorEvent event) {
        //textView.setText(String.valueOf(event.values[0]));
        if(event.values[0] < sensor.getMaximumRange()) {
            // Detected something nearby
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        } else {
            // Nothing is nearby
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
