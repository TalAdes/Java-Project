package com.example.liran.takeogo.controller.AddActivities;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.liran.takeogo.R;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;
import com.example.liran.takeogo.models.backend.TakeGoConst;


public class AddCarActivity extends Activity implements View.OnClickListener {
    private EditText idBranchCar;
    private EditText idCarModel;
    private EditText killometer;
    private EditText idCar;
    private Button addCarButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        findViews();
        IDBManager db = DBManagerFactory.getMnager();
    }

    private void findViews() {
        idBranchCar = (EditText)findViewById( R.id.idBranchCar );
        idCarModel = (EditText)findViewById( R.id.idCarModel );
        killometer = (EditText)findViewById( R.id.killometer );
        idCar = (EditText)findViewById( R.id.idCar );
        addCarButton = (Button)findViewById( R.id.addCarButton );

        addCarButton.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == addCarButton ) {
            addCar();
        }
    }

    private void addCar()
    {
        final Uri uri = TakeGoConst.CarConst.CarUri;
        final ContentValues contentValues = new ContentValues();

        try {
            long IdBranch = Long.valueOf(this.idBranchCar.getText().toString());
            long IdCarModel = Long.valueOf(this.idCarModel.getText().toString());
            long IdCar = Long.valueOf(this.idCar.getText().toString());
            int Killometer = Integer.valueOf(this.killometer.getText().toString());

            contentValues.put(TakeGoConst.CarConst.ID_CAR,IdCar);
            contentValues.put(TakeGoConst.CarConst.ID_BRANCH,IdBranch);
            contentValues.put(TakeGoConst.CarConst.ID_TYPE_MODEL,IdCarModel);
            contentValues.put(TakeGoConst.CarConst.KILLOMETER,Killometer);
        }
        catch (Exception e){
            Toast.makeText(AddCarActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        new AsyncTask<Void,Void,Uri>(){
            Exception error;

            @Override
            protected Uri doInBackground(Void... voids) {
                try {
                    return getContentResolver().insert(uri,contentValues);

                }
                catch (Exception e){
                    error = e;
                    return ContentUris.withAppendedId(Uri.parse("content://exception_cars"),-1);
                }

            }
            @Override
            protected void onPostExecute(Uri result) {
                super.onPostExecute(result);
                long id = ContentUris.parseId(result);

                if(error == null && id > 0)
                    Toast.makeText(AddCarActivity.this,"The Car include to dataBase!",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddCarActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
