package com.example.liran.takeogo.controller.AddActivities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;
import com.example.liran.takeogo.models.backend.TakeGoConst;

import java.io.ByteArrayOutputStream;

public class AddCarModel extends Activity implements View.OnClickListener {

    Integer SELECT_FILE=0;

    private EditText CompanyName;
    private EditText ModelName;
    private EditText ModelNum;
    private EditText EngineVolume;
    private EditText SeatsNumber;
    private Spinner GearboxSpinner;
    private ImageView ivimage;
    private ImageButton addImageButton;
    private Button AddButton;
    IDBManager db;
    private boolean idModel=false,nameComp=false,nameModel=false,engineCap=false,numberOfSeats=false;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_models);
        findViews();
    }


    private void findViews() {
        CompanyName = (EditText)findViewById( R.id.CompanyName );
        ModelName = (EditText)findViewById( R.id.ModelName );
        ModelNum = (EditText)findViewById( R.id.ModelNum );
        EngineVolume = (EditText)findViewById( R.id.EngineVolume );
        SeatsNumber = (EditText)findViewById( R.id.SeatsNumber );
        GearboxSpinner = (Spinner)findViewById( R.id.GearboxSpinner );
        addImageButton = (ImageButton)findViewById(R.id.imageButtonCarModel);
        ivimage = (ImageView)findViewById(R.id.ImageCarModel);
        AddButton = (Button)findViewById( R.id.AddButton );
        addImageButton.setOnClickListener(this);
        AddButton.setOnClickListener(this);
        AddButton.setEnabled(false);

        CompanyName.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable s) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals(""))
                    nameComp=false;
                else nameComp=true;
                checkOthers();
            }

        });
        ModelName.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable s) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals(""))
                    nameModel=false;
                else nameModel=true;
                checkOthers();
            }

        });
        ModelNum.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable s) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals(""))
                    idModel=false;
                else idModel=true;
                checkOthers();
            }

        });
        EngineVolume.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable s) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals(""))
                    engineCap=false;
                else engineCap=true;
                checkOthers();
            }

        });
        SeatsNumber.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable s) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals(""))
                    numberOfSeats=false;
                else numberOfSeats=true;
                checkOthers();
            }

        });
    }

    private void selectImage(){
        final CharSequence[] items = {"Gallery" ,"Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCarModel.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(items[i].equals("Gallery")){
                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent,SELECT_FILE);
                }
                else if(items[i].equals("Cancel")){
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==Activity.RESULT_OK){
            if(requestCode == SELECT_FILE){
                Uri selectImageUri = data.getData();
                this.ivimage.setImageURI(selectImageUri);
            }
        }
    }


    public void onClick(View v) {
        if ( v == AddButton ) {
            addCarModel();
        }
        if(v==addImageButton){
            selectImage();
        }
    }
    private void checkOthers() {
        if (idModel&&nameComp&&nameModel&&engineCap&&numberOfSeats)
        {
            AddButton.setEnabled(true);
        }
        else
        {
            AddButton.setEnabled(false);
        }
    }
    private String BmpToString(Bitmap bp)
    {
        ByteArrayOutputStream streme = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG,90,streme);
        byte [] arr = streme.toByteArray();
        String image_str = Base64.encodeToString(arr,Base64.DEFAULT);
        return image_str;
    }

    private void addCarModel() {
        final Uri uri = TakeGoConst.CarModelConst.CarModelsUri;
        final IDBManager db = DBManagerFactory.getMnager();

        final ContentValues contentValues = new ContentValues();
        try
        {
            long modelNum=Long.valueOf(ModelNum.getText().toString());
            int engineValue=Integer.valueOf(EngineVolume.getText().toString());
            int seatsNumber=Integer.valueOf(SeatsNumber.getText().toString());

            contentValues.put(TakeGoConst.CarModelConst.ID,modelNum);
            contentValues.put(TakeGoConst.CarModelConst.NAM_COMP,CompanyName.getText().toString());
            contentValues.put(TakeGoConst.CarModelConst.NAME,ModelName.getText().toString());
            contentValues.put(TakeGoConst.CarModelConst.ENGINE_CAP,engineValue);
            contentValues.put(TakeGoConst.CarModelConst.GEERBOX,GearboxSpinner.getSelectedItem().toString());
            contentValues.put(TakeGoConst.CarModelConst.NUMBER_OF_SEATS,seatsNumber);
            Toast.makeText(this,ModelName.getText().toString(),Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(AddCarModel.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        new AsyncTask<Void,Void,String>() {
            String str;
            @Override
            protected String doInBackground(Void... voids) {
                Bitmap bmp  = ((BitmapDrawable)ivimage.getDrawable()).getBitmap();
                String ImageStr = BmpToString(bmp);
                contentValues.put(TakeGoConst.CarModelConst.IMAGE,ImageStr);
                str = db.addCarModel(contentValues);
                return str;
            }

            @Override
            protected void onPostExecute(String str) {
                if(str!="error")
                {
                    Toast.makeText(AddCarModel.this, str, Toast.LENGTH_SHORT).show();
                    finish();
                }
                else Toast.makeText(AddCarModel.this, "This client is already exists!", Toast.LENGTH_SHORT).show();
            }
        }.execute();

    }

}
