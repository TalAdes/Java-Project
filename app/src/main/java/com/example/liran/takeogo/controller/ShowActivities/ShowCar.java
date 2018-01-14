package com.example.liran.takeogo.controller.ShowActivities;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.controller.Adapters.CarCursorAdapter;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;

import java.util.ArrayList;
import java.util.List;

public class ShowCar extends Activity {

    private IDBManager db;
    private Spinner spinner;
    private ProgressBar progressBar;

    private ListView listView;
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car);
        progressBar = (ProgressBar)findViewById(R.id.CarProgressBar);
        db = DBManagerFactory.getMnager();
        spinner   = (Spinner)findViewById(R.id.chosen_carModel);

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
                    if(lst.isEmpty())
                    {
                        Toast.makeText(ShowCar.this,"There was an error to connect to internet",Toast.LENGTH_SHORT);
                        finish();
                    }

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ShowCar.this,"Connection to DataBase Success",Toast.LENGTH_SHORT);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ShowCar.this, android.R.layout.simple_spinner_item, lst);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    custonMain();
                }
            }.execute();
        }
        catch (Exception e) {Toast.makeText(this,"fail :(",Toast.LENGTH_SHORT);}
    }

    private void custonMain() {
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spinner.getSelectedItem().toString();
                ShowChooosenModel(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void ShowChooosenModel(final String selected) {
        listView = (ListView) findViewById(R.id.carListView);
        try {
            new AsyncTask<Void,Void,Cursor>(){
                @Override
                protected Cursor doInBackground(Void... voids) {
                    Cursor c;
                    try
                    {   c=db.getCarByModels(selected);
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
                    db.dummyOperation();

                    listView.setAdapter(new CarCursorAdapter(ShowCar.this,result,0 ));
                    Toast.makeText(ShowCar.this,"succes :)",Toast.LENGTH_SHORT);
                }
            }.execute();
        }
        catch (Exception e) {Toast.makeText(this,"fail :(",Toast.LENGTH_SHORT);}
    }
}
