package com.example.liran.takeogo.controller.AddActivities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;

import java.util.ArrayList;


public class AddCarActivity extends Activity implements View.OnClickListener {
    private EditText killometer;
    private EditText idCar;
    private Button addCarButton;
    private AutoCompleteTextView CompanyAutoCompleteTextView,ModelAutoCompleteTextView,ModelNumAutoCompleteTextView,BranchAutoCompleteTextView;
    private IDBManager db;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        db = DBManagerFactory.getMnager();
        findViews();
    }

    private void findViews() {
        killometer = (EditText)findViewById( R.id.killometer );
        idCar = (EditText)findViewById( R.id.idCar );
        CompanyAutoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.CompanyAutoCompleteTextView);
        ModelAutoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.ModelAutoCompleteTextView);
        ModelNumAutoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.ModelNumAutoCompleteTextView);
        BranchAutoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.BranchAutoCompleteTextView);
        addCarButton = (Button)findViewById( R.id.addCarButton );

        ModelAutoCompleteTextView.setEnabled(false);
        ModelNumAutoCompleteTextView.setEnabled(false);

        CompanyAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                s = CompanyAutoCompleteTextView.getText().toString();
                if(!db.getAllCompanies().contains(s))
                {
                    ModelAutoCompleteTextView.setEnabled(false);
                    ModelAutoCompleteTextView.setTextColor(Color.RED);
                }
                else
                {
                    ModelAutoCompleteTextView.setTextColor(Color.BLACK);
                    ArrayList<String> models = db.getModelsByCompany(s);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddCarActivity.this,android.R.layout.simple_list_item_1,models);
                    ModelAutoCompleteTextView.setAdapter(adapter);
                    ModelAutoCompleteTextView.setEnabled(true);
                }
            }
        });

        /*
        CompanyAutoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s = CompanyAutoCompleteTextView.getText().toString();
                if(!db.getAllCompanies().contains(s))
                {
                    ModelAutoCompleteTextView.setEnabled(false);
                    ModelAutoCompleteTextView.setTextColor(Color.RED);
                    return;
                }
                ModelAutoCompleteTextView.setTextColor(Color.BLACK);
                ArrayList<String> models = db.getModelsByCompany(s);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddCarActivity.this,android.R.layout.simple_list_item_1,models);
                ModelAutoCompleteTextView.setAdapter(adapter);
                ModelAutoCompleteTextView.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */
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
        /*
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
                    return null;
                }

            }
            @Override
            protected void onPostExecute(Uri result) {
                super.onPostExecute(result);
                long id = ContentUris.parseId(result);

                if(!result.equals("content://exception_cars") && id > 0)
                    Toast.makeText(AddCarActivity.this,"The Car include to dataBase!",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddCarActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }.execute();
        */
    }
}
