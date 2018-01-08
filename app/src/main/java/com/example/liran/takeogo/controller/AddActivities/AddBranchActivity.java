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
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;
import com.example.liran.takeogo.models.backend.TakeGoConst;

import java.io.ByteArrayOutputStream;

public class AddBranchActivity extends Activity implements View.OnClickListener {

//<<<<<<< HEAD
    Integer SELECT_FILE=0;
    //Integer REQUEST_CAMERA=1,SELECT_FILE=0;
//=======

//>>>>>>> c33f7e14c09953403dc8eb746e6c5d45a35a9c91

    private EditText cityEditText;
    private EditText streetEditText;
    private EditText numApartEditText;
    private EditText idBranchEditText;
    private EditText numParkingEditText;
    private ImageView ivimage;
    private ImageButton addImageButton;
    private Button addBranchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);
        findViews();
    }

    private void findViews() {
        cityEditText = (EditText)findViewById( R.id.cityEditText );
        streetEditText = (EditText)findViewById( R.id.streetEditText );
        numApartEditText = (EditText)findViewById( R.id.numApartEditText );
        idBranchEditText = (EditText)findViewById( R.id.idBranchEditText );
        numParkingEditText = (EditText)findViewById( R.id.numParkingEditText );
        ivimage = (ImageView)findViewById(R.id.ivImage);

        addImageButton = (ImageButton)findViewById(R.id.imageButton);
        addBranchButton = (Button)findViewById( R.id.addBranchButton );

        addImageButton.setOnClickListener(this);
        addBranchButton.setOnClickListener( this );
    }

    private void selectImage(){
        final CharSequence[] items = {"Gallery" ,"Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddBranchActivity.this);
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

    @Override public void onClick(View v) {
        if ( v == addBranchButton ) {
            addBranch();
        }
        if(v==addImageButton){
            selectImage();
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

    private void addBranch(){
        final IDBManager db = DBManagerFactory.getMnager();
        final ContentValues contentValues = new ContentValues();

            contentValues.put(TakeGoConst.BranchConst.ID,Long.valueOf(this.idBranchEditText.getText().toString()));
            contentValues.put(TakeGoConst.BranchConst.CITY,this.cityEditText.getText().toString());
            contentValues.put(TakeGoConst.BranchConst.STREET,this.streetEditText.getText().toString());
            contentValues.put(TakeGoConst.BranchConst.NUM_APARTMENT,Integer.valueOf(this.numApartEditText.getText().toString()));
            contentValues.put(TakeGoConst.BranchConst.NUMBER_PARKING,Integer.valueOf(this.numParkingEditText.getText().toString()));



        new AsyncTask<Void,Void,String>() {
            String str;

            @Override
            protected String doInBackground(Void... voids) {
                Bitmap bmp = ((BitmapDrawable)ivimage.getDrawable()).getBitmap();
                String ImageStr = BmpToString(bmp);
                contentValues.put(TakeGoConst.BranchConst.IMAGE,ImageStr.toString());
                str = db.addBranch(contentValues);
                return str;
            }

            @Override
            protected void onPostExecute(String str) {
                if(str!="error")
                {
                    Toast.makeText(AddBranchActivity.this, str, Toast.LENGTH_SHORT).show();
                    finish();
                }
                else Toast.makeText(AddBranchActivity.this, "This client is already exists!", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

}
