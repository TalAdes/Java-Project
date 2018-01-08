package com.example.liran.takeogo.controller.ShowActivities;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.controller.Adapters.CarModelCursorAdapter;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;

import java.util.ArrayList;
import java.util.List;

public class ShowCar extends Activity {

    private IDBManager db;
    private Spinner spinner;

    private ListView listView;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car);
        db = DBManagerFactory.getMnager();
        spinner   = (Spinner)findViewById(R.id.chosen_carModel);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spinner.getSelectedItem().toString();
                letHimChooose(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        try {
            new AsyncTask<Void,Void,List<String>>(){
                @Override
                protected List<String> doInBackground(Void... voids) {
                    List<String> spinnerArray =  new ArrayList<String>();
                    try
                    {
                        spinnerArray=db.getModelName();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(ShowCar.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        return null;
                    }
                    return spinnerArray;
                }
                @Override protected void onPostExecute(List<String> lst) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ShowCar.this, android.R.layout.simple_spinner_item, lst);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }
            }.execute();
        }
        catch (Exception e) {Toast.makeText(this,"fail :(",Toast.LENGTH_SHORT);}
    }


    private void letHimChooose(final String selected) {
        listView = (ListView) findViewById(R.id.carListView);
        try {
            new AsyncTask<Void,Void,Cursor>(){
                @Override
                protected Cursor doInBackground(Void... voids) {
                    Cursor c;
                    try
                    {
                        c=db.getCarByModels(selected);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(ShowCar.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        return null;
                    }
                    return c;
                }
                @Override
                protected void onPostExecute(Cursor result) {
                    listView.setAdapter(new CarModelCursorAdapter(ShowCar.this,result,0 ));
                    Toast.makeText(ShowCar.this,"succes :)",Toast.LENGTH_SHORT);
                }
            }.execute();
        }
        catch (Exception e) {Toast.makeText(this,"fail :(",Toast.LENGTH_SHORT);}
    }
}
