package com.example.liran.takeogo.controller.ShowActivities;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.controller.Adapters.CarModelCursorAdapter;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;

public class ShowCarModels extends Activity {

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car_models_db);
        final IDBManager db = DBManagerFactory.getMnager();
        final ListView listView = (ListView) findViewById(R.id.carModelListView);
        progressBar = (ProgressBar)findViewById(R.id.carModelsProgressBar);

        try {
            new AsyncTask<Void,Void,Cursor>(){
                @Override
                protected Cursor doInBackground(Void... voids) {
                    Cursor c;
                    try
                    {
                        c = db.getCarModels();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(ShowCarModels.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        return null;
                    }
                    return c;
                }
                @Override
                protected void onPostExecute(Cursor result) {
                    if(result==null)
                    {
                        Toast.makeText(ShowCarModels.this,"There was an error to connect to internet",Toast.LENGTH_SHORT);
                        finish();
                    }

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ShowCarModels.this,"Connection to DataBase Success",Toast.LENGTH_SHORT);
                    listView.setAdapter(new CarModelCursorAdapter(ShowCarModels.this,result,0 ));
                    Toast.makeText(ShowCarModels.this,"succes :)",Toast.LENGTH_SHORT);
                }
            }.execute();
        }
        catch (Exception e) {Toast.makeText(this,"fail :(",Toast.LENGTH_SHORT);}    }


}
