package com.example.liran.takeogo.controller.AddActivities;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;
import com.example.liran.takeogo.models.backend.TakeGoConst;

public class AddCarModel extends Activity implements View.OnClickListener {

    private EditText CompanyName;
    private EditText ModelName;
    private EditText ModelNum;
    private EditText EngineVolume;
    private EditText SeatsNumber;
    private Spinner GearboxSpinner;
    private Button AddButton;
    IDBManager db;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_models);
        IDBManager db = DBManagerFactory.getMnager();
        findViews();
    }


    private void findViews() {
        CompanyName = (EditText)findViewById( R.id.CompanyName );
        ModelName = (EditText)findViewById( R.id.ModelName );
        ModelNum = (EditText)findViewById( R.id.ModelNum );
        EngineVolume = (EditText)findViewById( R.id.EngineVolume );
        SeatsNumber = (EditText)findViewById( R.id.SeatsNumber );
        GearboxSpinner = (Spinner)findViewById( R.id.GearboxSpinner );
        AddButton = (Button)findViewById( R.id.AddButton );

        AddButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if ( v == AddButton ) {
            addCarModel();
        }
    }

    private void addCarModel() {
        final Uri uri = TakeGoConst.CarModelConst.CarModelsUri;
        final ContentValues contentValues = new ContentValues();
        try
        {
            long modelNum=Long.valueOf(ModelNum.getText().toString());
            int engineValue=Integer.valueOf(EngineVolume.getText().toString());
            int seatsNumber=Integer.valueOf(SeatsNumber.getText().toString());

            contentValues.put(TakeGoConst.CarModelConst.ID,modelNum);
            contentValues.put(TakeGoConst.CarModelConst.ENGINE_CAP,engineValue);
            contentValues.put(TakeGoConst.CarModelConst.NUMBER_OF_SEATS,seatsNumber);
            contentValues.put(TakeGoConst.CarModelConst.NAM_COMP,CompanyName.getText().toString());
            contentValues.put(TakeGoConst.CarModelConst.NAME,ModelName.getText().toString());
            contentValues.put(TakeGoConst.CarModelConst.GEERBOX,GearboxSpinner.getSelectedItem().toString());

        }
        catch (Exception e)
        {
            Toast.makeText(AddCarModel.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        new AsyncTask<Void,Void,Uri>() {
            Exception error;

            @Override
            protected Uri doInBackground(Void... voids) {
                try {
                    return getContentResolver().insert(uri,contentValues);
                } catch (Exception e) {
                    error = e;
                    return ContentUris.withAppendedId(Uri.parse("content://exception_carModel"),-1);
                }
            }

            @Override
            protected void onPostExecute(Uri result) {
                super.onPostExecute(result);
                long id = ContentUris.parseId(result);
                if (error == null && id > 0)
                    Toast.makeText(AddCarModel.this, "The car model include to dataBase!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddCarModel.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }.execute();

    }

}
