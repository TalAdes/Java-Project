package com.example.liran.takeogo.controller.ShowActivities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.controller.Adapters.CarModelCursorAdapter;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;

public class ShowCarModels extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car_models_db);
        IDBManager db = DBManagerFactory.getMnager();
        ListView listView = (ListView) findViewById(R.id.carModelListView);

        listView.setAdapter(new CarModelCursorAdapter(ShowCarModels.this,db.getCarModels(),0 ));
    }


}
