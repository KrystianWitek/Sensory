package com.example.krystian.myapp;

import java.io.IOException;
import java.text.ChoiceFormat;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
//import android.support.v4.widget.SearchViewCompatIcs;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.database.Cursor;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Krystian on 29.06.2017.
 */

public class GetCurrentLocation extends Activity implements OnClickListener {

    private LocationManager locationManager = null;
    private LocationListener locationListener = null;

    private Button btnGetLocation = null;
    private EditText editLocation = null;
    private ProgressBar pb = null;

    private static final String TAG = "Debug";
    private Boolean flag = false;

    public double longitude = 0;
    public double latitude = 0;
    public String cityName = "Koniec Swiata";
    public String currentDate = "0";

    Button btnViewAllData = null;
    Button btnAddNewLocation = null;

    ArrayList<MyLocation> listaLokalizacji;
    DatabaseHelper db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_location);

        db = new DatabaseHelper(this);

        //if you want to lock screen for always Portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        editLocation = (EditText) findViewById(R.id.editTextLocation);

        btnGetLocation = (Button) findViewById(R.id.btnLocation);
        btnGetLocation.setOnClickListener(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        btnViewAllData = (Button) findViewById(R.id.btnViewBase);

        listaLokalizacji = new ArrayList<MyLocation>();
        btnAddNewLocation = (Button) findViewById(R.id.addToBase);
        btnAddNewLocation.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        addData(longitude,latitude,cityName,getCurrentDate());
                    }
                });

        btnViewAllData.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                       viewAll();
                    }
                });


    }

    public void onClick(View v) {
        flag = displayGpsStatus();
        if (flag) {

            Log.v(TAG, "onClick");

            editLocation.setText("Proszę poruszaj telefonem," +
                    "abym szybciej znalazł, gdzie się zgubiłeś ;)" + "\n");

            pb.setVisibility(View.VISIBLE);
            locationListener = new MyLocationListener();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager
                    .GPS_PROVIDER, 5000, 10, locationListener);

        } else {
            alertbox("Status GPS!!", "Twój GPS jest: WYŁĄCZONY");
        }
    }

         /*----Method to Check GPS is enable or disable ----- */
        private Boolean displayGpsStatus() {
            ContentResolver contentResolver = getBaseContext().getContentResolver();
            boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER);
            if(gpsStatus) {
                return true;
            }
            else {
                return false;
            }
        }

        /*----------Method to create an AlertBox ------------- */
        protected void alertbox(String title, String mymessage){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Twój GPS jest wyłączony")
                    .setCancelable(false).setTitle("** Status GPS **")
                    .setPositiveButton("Gps Włączone", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            // finish the current activity
                            // AlertBoxAdvance.this.finish();
                            Intent myIntent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
                            startActivity(myIntent);
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Anuluj", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            // cancel dialog box
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        private class MyLocationListener implements LocationListener{

            public void onLocationChanged(Location loc) {

                editLocation.setText("");
                pb.setVisibility(View.INVISIBLE);

                //Toast.makeText(getBaseContext(), "Nowa lokacja: N: " + loc.getLatitude() + "E: "
                       // + loc.getLongitude(), Toast.LENGTH_SHORT).show();

                longitude = loc.getLongitude();
                String lon = "Szerokość : " + longitude;
                Log.v(TAG, lon);

                latitude = loc.getLatitude();
                String lat = "Długość : " + latitude;
                Log.v(TAG, lat);

                /*----------to get City-Name from coordinates ------------- */

                cityName = null;
                Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                List<Address> addresses;

                try {
                    addresses = gcd.getFromLocation(loc.getLatitude(), loc
                            .getLongitude(), 1);
                    if (addresses.size() > 0)
                        System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String s = lon + "\n" + lat + "\n\n Obcecne miasto: " + cityName;
                editLocation.setText(s);

            }

            public void onProviderDisabled(String provider){}

            public void onProviderEnabled(String provider){}

            public void onStatusChanged(String provider, int status, Bundle extras){}
        }

        public String getCurrentDate(){

            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
            String date = df.format(Calendar.getInstance().getTime());

            Date currentNewDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dateString = dateFormat.format(currentNewDate);
            //return dateString + Calendar.getInstance().getTime().toString();
            return date;
        }

    public void addData(double longitude, double latitude, String cityName, String my_Date) {

        if(cityName != "Koniec Swiata") {
            boolean isInsered = db.addData(longitude, latitude, cityName, my_Date);

            MyLocation newLoc = new MyLocation(longitude, latitude, cityName);
            listaLokalizacji.add(newLoc);

            if (isInsered == true) {
                Toast.makeText(getBaseContext(), "Dane zapisane", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(getBaseContext(), "Błąd zapisu danych", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getBaseContext(), "Wczytaj najpierw lokalizacje", Toast.LENGTH_LONG).show();
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void viewAll() {

        Toast.makeText(getApplicationContext(),"Co się zobaczyło, to się nie odzobaczy",Toast.LENGTH_LONG).show();

        Cursor res = db.getAllData();
        if(res.getCount() == 0) {
            // show message
            //Toast.makeText(getApplicationContext(),"PUSTO",Toast.LENGTH_SHORT).show();
            showMessage("Błąd" , "Nic nie znaleziono");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while( res.moveToNext()) {
            buffer.append("ID : " + res.getString(0) + "\n");
            buffer.append("Szerokość : " + res.getDouble(1) + "\n");
            buffer.append("Długość : " + res.getDouble(2) + "\n");
            buffer.append("Miasto : " + res.getString(3) + "\n");
            buffer.append("Data : " + res.getString(4) + "\n\n" );
        }

        // show all data
        showMessage("Data", buffer.toString());

    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public ArrayList<MyLocation> getArrayList() {
        return listaLokalizacji;
    }
}
