package com.example.liran.takeogo.controller.AddActivities;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;
import com.example.liran.takeogo.models.backend.TakeGoConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AddCarActivity extends Activity implements View.OnClickListener {
    private EditText killometer;
    private EditText idCar;
    private Button addCarButton;
    private AutoCompleteTextView CompanyAutoCompleteTextView,ModelAutoCompleteTextView,ModelNumAutoCompleteTextView,BranchAutoCompleteTextView;
    private IDBManager db;
    private String s;
    private ArrayList<String> allBranches,allCompanies,relevantModels,relevantCodes;
    private Map<String,ArrayList<String>> compDict,modelDict;
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
        addCarButton.setOnClickListener( this );

        CompanyAutoCompleteTextView.setThreshold(1);
        ModelAutoCompleteTextView.setThreshold(1);
        ModelNumAutoCompleteTextView.setThreshold(1);
        BranchAutoCompleteTextView.setThreshold(1);
        CompanyAutoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { CompanyAutoCompleteTextView.showDropDown(); }
        });
        ModelAutoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ModelAutoCompleteTextView.showDropDown(); }
        });
        ModelNumAutoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ModelNumAutoCompleteTextView.showDropDown(); }
        });
        BranchAutoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ModelNumAutoCompleteTextView.showDropDown(); }
        });
        CompanyAutoCompleteTextView.setEnabled(false);
        ModelAutoCompleteTextView.setEnabled(false);
        ModelNumAutoCompleteTextView.setEnabled(false);
        BranchAutoCompleteTextView.setEnabled(false);

        //initial activity DB
        new AsyncTask<Void, Void, ArrayList<String>>() {
            @Override protected ArrayList<String> doInBackground(Void... voids) {
                try {
                    allCompanies = db.getAllCompanies();
                    allBranches = db.getBranchesCodes();
                    compDict = new HashMap<String,ArrayList<String>>();
                    modelDict = new HashMap<String,ArrayList<String>>();
                    return allCompanies;
                } catch (Exception e) {
                    return new ArrayList<String>();
                }
            }

            @Override
            protected void onPostExecute(ArrayList<String> allCompanies) {
                if (allCompanies.isEmpty())
                    Toast.makeText(AddCarActivity.this,"There was an error to connect to internet",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddCarActivity.this,"Connection to Companies DataBase Success",Toast.LENGTH_SHORT).show();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddCarActivity.this,android.R.layout.simple_list_item_1,allCompanies);
                CompanyAutoCompleteTextView.setAdapter(adapter);
                CompanyAutoCompleteTextView.setEnabled(true);
            }
        }.execute();

        //Listeners that look after the hierarchical association between the car's AutoFeels
        CompanyAutoCompleteTextView.addTextChangedListener(new TextWatcher()
        {
           @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
           @Override public void afterTextChanged(Editable s) { }
           @Override public void onTextChanged(CharSequence sr, int start, int before, int count)
           {
               s = CompanyAutoCompleteTextView.getText().toString();
               if (!allCompanies.contains(s))
               {
                   CompanyAutoCompleteTextView.setTextColor(Color.RED);
                   ModelAutoCompleteTextView.setEnabled(false);
                   ModelNumAutoCompleteTextView.setEnabled(false);
               }
               else
               {
                   CompanyAutoCompleteTextView.setTextColor(Color.BLACK);
                   new AsyncTask<Void, Void, ArrayList<String>>()
                   {
                       @Override protected ArrayList<String> doInBackground(Void... voids)
                       {
                           try
                           {
                               if(compDict.get(s)==null)
                                   compDict.put(s, db.getModelsByCompany(s));
                               return compDict.get(s);
                           }
                           catch (Exception e)
                           {
                               Toast.makeText(AddCarActivity.this, "There was an error to connect to internet", Toast.LENGTH_SHORT);
                               return new ArrayList<String>();
                           }
                       }

                       @Override protected void onPostExecute(ArrayList<String> relevantModels)
                       {
                           ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddCarActivity.this, android.R.layout.simple_list_item_1, relevantModels);
                           db.dummyOperation();
                           ModelAutoCompleteTextView.setAdapter(adapter);
                           ModelAutoCompleteTextView.setText("");
                           ModelAutoCompleteTextView.setEnabled(true);
                           ModelAutoCompleteTextView.setTextColor(Color.BLACK);
                       }
                   }.execute();
               }
           }
       });
        ModelAutoCompleteTextView.addTextChangedListener(new TextWatcher()
        {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable s) { }
            @Override public void onTextChanged(CharSequence sr, int start, int before, int count)
            {
                s = ModelAutoCompleteTextView.getText().toString();
                relevantModels=compDict.get(CompanyAutoCompleteTextView.getText().toString());
                if (!relevantModels.contains(s))
                {
                    ModelAutoCompleteTextView.setTextColor(Color.RED);
                    ModelNumAutoCompleteTextView.setEnabled(false);
                }
                else
                {
                    ModelAutoCompleteTextView.setTextColor(Color.BLACK);
                    new AsyncTask<Void, Void, ArrayList<String>>()
                    {
                        @Override protected ArrayList<String> doInBackground(Void... voids)
                        {
                            try
                            {
                                if(modelDict.get(s)==null)
                                    modelDict.put(s, db.getcodeByModel(s));
                                return modelDict.get(s);
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(AddCarActivity.this, "There was an error to connect to internet", Toast.LENGTH_SHORT);
                                return new ArrayList<String>();
                            }
                        }

                        @Override protected void onPostExecute(ArrayList<String> relevantCodes)
                        {
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddCarActivity.this, android.R.layout.simple_list_item_1, relevantCodes);
                            ModelNumAutoCompleteTextView.setText("");
                            ModelNumAutoCompleteTextView.setAdapter(adapter);
                            ModelNumAutoCompleteTextView.setEnabled(true);
                            ModelNumAutoCompleteTextView.setTextColor(Color.BLACK);
                        }
                    }.execute();
                }
            }
        });
        ModelNumAutoCompleteTextView.addTextChangedListener(new TextWatcher()
        {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable s) { }
            @Override public void onTextChanged(CharSequence sr, int start, int before, int count)
            {
                s = ModelNumAutoCompleteTextView.getText().toString();
                relevantCodes=modelDict.get(ModelAutoCompleteTextView.getText().toString());
                if (!relevantCodes.contains(s))
                {
                    ModelNumAutoCompleteTextView.setTextColor(Color.RED);
                }
                else
                {
                    ModelNumAutoCompleteTextView.setTextColor(Color.BLACK);
                }
            }
        });

        //Listener for the branch's AutoFeel
        BranchAutoCompleteTextView.addTextChangedListener(new TextWatcher()
        {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable s) { }
            @Override public void onTextChanged(CharSequence sr, int start, int before, int count)
            {
                s = BranchAutoCompleteTextView.getText().toString();
                if (!allBranches.contains(s))
                {
                    BranchAutoCompleteTextView.setTextColor(Color.RED);
                }
                else
                {
                    CompanyAutoCompleteTextView.setTextColor(Color.BLACK);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if ( v == addCarButton ) {
            addCar();
        }
    }

    private void addCar() {
        final ContentValues contentValues = new ContentValues();


        contentValues.put(TakeGoConst.CarConst.ID_BRANCH,this.BranchAutoCompleteTextView.getText().toString());
        contentValues.put(TakeGoConst.CarConst.ID_CAR,this.idCar.getText().toString());
        contentValues.put(TakeGoConst.CarConst.ID_TYPE_MODEL,this.ModelNumAutoCompleteTextView.getText().toString());
        contentValues.put(TakeGoConst.CarConst.KILLOMETER,this.killometer.getText().toString());
        contentValues.put(TakeGoConst.CarConst.MODEL_NAME,this.ModelAutoCompleteTextView.getText().toString());


        new AsyncTask<Void,Void,String>() {
            String str;
            @Override
            protected String doInBackground(Void... voids) {
                str = db.addClient(contentValues);
                return str;
            }

            @Override
            protected void onPostExecute(String str) {
                if(str!="error")
                {
                    Toast.makeText(AddCarActivity.this, str, Toast.LENGTH_SHORT).show();
                    finish();
                }
                else Toast.makeText(AddCarActivity.this, "This client is already exists!", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
