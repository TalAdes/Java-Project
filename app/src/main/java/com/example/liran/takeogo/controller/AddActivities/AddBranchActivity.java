package com.example.liran.takeogo.controller.AddActivities;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

public class AddBranchActivity extends Activity implements View.OnClickListener {

    Integer REQUEST_CAMERA=1,SELECT_FILE=0;

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
        IDBManager db = DBManagerFactory.getMnager();
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
        final CharSequence[] items = {"Camera","Gallery" ,"Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddBranchActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(items[i].equals("Camera")){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);
                }
                else if(items[i].equals("Gallery")){
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
            if(requestCode == REQUEST_CAMERA){
                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                this.ivimage.setImageBitmap(bmp);
            }
            else if(requestCode == SELECT_FILE){
                Uri selectImageUri = data.getData();
                this.ivimage.setImageURI(selectImageUri);
            }

        }


    }

    @Override
    public void onClick(View v) {
        if ( v == addBranchButton ) {
            addBranch();
        }
        if(v==addImageButton){
            selectImage();
        }

    }

    private void addBranch(){
        final Uri uri = TakeGoConst.BranchConst.BranchUri;
        final ContentValues contentValues = new ContentValues();

        try {
            contentValues.put(TakeGoConst.BranchConst.ID,Long.valueOf(this.idBranchEditText.getText().toString()));
            contentValues.put(TakeGoConst.BranchConst.CITY,this.cityEditText.getText().toString());
            contentValues.put(TakeGoConst.BranchConst.STREET,this.streetEditText.getText().toString());
            contentValues.put(TakeGoConst.BranchConst.NUM_APARTMENT,Integer.valueOf(this.numApartEditText.getText().toString()));
            contentValues.put(TakeGoConst.BranchConst.NUMBER_PARKING,Integer.valueOf(this.numParkingEditText.getText().toString()));

        }
        catch (Exception e)
        {
            Toast.makeText(AddBranchActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        new AsyncTask<Void,Void,Uri>()
        {
            Exception error;
            @Override
            protected Uri doInBackground(Void... voids)
            {
                try
                {
                    return getContentResolver().insert(uri,contentValues);
                }
                catch (Exception e)
                {
                    error = e;
                    return ContentUris.withAppendedId(Uri.parse("content://exception_branch"),-1);
                }
            }

            @Override
            protected void onPostExecute(Uri result)
            {
                //Log.i("Kdfd",result.toString());
                super.onPostExecute(result);
                long id = ContentUris.parseId(result);
                if( error == null && id > 0)
                    Toast.makeText(AddBranchActivity.this,"The Branch include to dataBase!",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddBranchActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

}