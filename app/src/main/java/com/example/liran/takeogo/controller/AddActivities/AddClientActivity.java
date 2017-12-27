package com.example.liran.takeogo.controller.AddActivities;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;
import com.example.liran.takeogo.models.backend.TakeGoConst;

import java.util.regex.Pattern;

public class AddClientActivity extends Activity implements View.OnClickListener  {

    private EditText fnameEditText;
    private EditText lnameEditText;
    private EditText idEditText;
    private EditText phoneNumEditText;
    private TextView phoneTextView;
    private EditText emailEditText;
    private TextView emailTextView;
    private EditText numCreditEditText;
    private TextView numCreTextView;
    private Button addClientButton;
    private IDBManager db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        db = DBManagerFactory.getMnager();
        findViews();
    }


    private void findViews() {
        fnameEditText = (EditText)findViewById( R.id.fnameEditText );
        lnameEditText = (EditText)findViewById( R.id.lnameEditText );
        idEditText = (EditText)findViewById( R.id.idEditText );
        phoneNumEditText = (EditText)findViewById( R.id.phoneNumEditText );
        phoneTextView = (TextView)findViewById(R.id.phone_valid_TW);
        emailEditText = (EditText)findViewById( R.id.emailEditText );
        emailTextView = (TextView)findViewById((R.id.email_valid_TW));
        numCreditEditText = (EditText)findViewById( R.id.numCreditEditText );
        numCreTextView=(TextView)findViewById(R.id.credit_card_valid_TW);
        addClientButton = (Button)findViewById( R.id.addClientButton );

        addClientButton.setOnClickListener( this );
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s){}

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern ptr = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
                String email = emailEditText.getText().toString();
                if (ptr.matcher(email).matches())
                {
                    emailTextView.setText("");
                }
                else
                {
                    emailTextView.setText(email + " is " + "invalid");
                    emailTextView.setTextColor(Color.RED);
                }
            }
        });
        phoneNumEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern ptrMobile05 = Pattern.compile("^[0][5][02348][0-9]{7}$",Pattern.CASE_INSENSITIVE);
                Pattern ptrMobile077 = Pattern.compile("^[0][7][7][0-9]{7}$",Pattern.CASE_INSENSITIVE);
                Pattern ptrTelephon = Pattern.compile("^[0][2-9][0-9]{7}$",Pattern.CASE_INSENSITIVE);
                String phone = phoneNumEditText.getText().toString();
                if (ptrMobile05.matcher(phone).matches()||ptrMobile077.matcher(phone).matches()||ptrTelephon.matcher(phone).matches() )
                {
                    phoneTextView.setText("");
                }
                else
                {
                    phoneTextView.setText(phone + " is an invalid phone number");
                    phoneTextView.setTextColor(Color.RED);
                }
            }
        });
        numCreditEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern credinNum = Pattern.compile("^[0-9]{16}$");
                String phone = numCreditEditText.getText().toString();
                if (credinNum.matcher(phone).matches())
                {
                    numCreTextView.setText("");
                }
                else
                {
                    numCreTextView.setText(phone + " is an invalid credit card number");
                    numCreTextView.setTextColor(Color.RED);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if ( v == addClientButton ) {
            addClient();
        }
    }

    private void addClient(){
        final Uri uri = TakeGoConst.ClientConst.ClientsUri;
        final ContentValues contentValues = new ContentValues();

        try {
            contentValues.put(TakeGoConst.ClientConst.FIRST_NAME,this.fnameEditText.getText().toString());
            contentValues.put(TakeGoConst.ClientConst.LAST_NAME,this.lnameEditText.getText().toString());
            contentValues.put(TakeGoConst.ClientConst.ID,Long.valueOf(this.idEditText.getText().toString()));
            contentValues.put(TakeGoConst.ClientConst.PHONE_NUMBER,this.phoneNumEditText.getText().toString());
            contentValues.put(TakeGoConst.ClientConst.EMAIL,this.emailEditText.getText().toString());
            contentValues.put(TakeGoConst.ClientConst.NUM_CREDIT,Long.valueOf(this.numCreditEditText.getText().toString()));


            new AsyncTask<Void,Void,Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        db.addClient(contentValues);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute();

            finish();
        }
        catch (Exception e)
        {
            Toast.makeText(AddClientActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

}

