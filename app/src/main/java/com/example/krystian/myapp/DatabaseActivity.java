package com.example.krystian.myapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Krystian on 06.07.2017.
 */

public class DatabaseActivity extends Activity {


    DatabaseHelper myDb;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);

    }
    //Toast.makeText(getBaseContext(),cityName, Toast.LENGTH_SHORT).show();




    /**/


}
