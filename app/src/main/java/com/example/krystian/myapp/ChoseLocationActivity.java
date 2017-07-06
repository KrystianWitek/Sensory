package com.example.krystian.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krystian on 06.07.2017.
 */

public class ChoseLocationActivity extends Activity {

    ListView list;
    ArrayAdapter<MyLocation> adapter;
    ArrayList<MyLocation> lista;
    DatabaseHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_localisations);

        db = new DatabaseHelper(getBaseContext());

        list = (ListView) findViewById(R.id.listView1);
        GetCurrentLocation gt = new GetCurrentLocation();
        lista = gt.getArrayList();

        adapter = new ArrayAdapter<MyLocation>(this, R.layout.row, lista);

        list.setAdapter(adapter);
    }
}
