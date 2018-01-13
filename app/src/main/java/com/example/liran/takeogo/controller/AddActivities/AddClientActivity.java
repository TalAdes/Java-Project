package com.example.liran.takeogo.controller.AddActivities;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Color;
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

    private EditText lnameEditText;
    private TextView valid_fname;
    private EditText fnameEditText;
    private TextView valid_lname;
    private EditText idEditText;
    private TextView idTextView;
    private EditText phoneNumEditText;
    private TextView phoneTextView;
    private EditText emailEditText;
    private TextView emailTextView;
    private EditText numCreditEditText;
    private TextView numCreTextView;
    private Button addClientButton;
    private IDBManager db;
    private boolean id=false,credit=false,phonen=false,fname=false,lname=false,e_mail=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        db = DBManagerFactory.getMnager();
        findViews();
    }


    private void findViews() {

        fnameEditText = (EditText)findViewById( R.id.fnameEditText );
        valid_fname = (TextView) findViewById( R.id.valid_fname );

        lnameEditText = (EditText)findViewById( R.id.lnameEditText );
        valid_lname = (TextView) findViewById( R.id.valid_lname );

        idEditText = (EditText)findViewById( R.id.idEditText );
        idTextView = (TextView) findViewById( R.id.id_valid_TW );

        phoneNumEditText = (EditText)findViewById( R.id.phoneNumEditText );
        phoneTextView = (TextView)findViewById(R.id.phone_valid_TW);

        emailEditText = (EditText)findViewById( R.id.emailEditText );
        emailTextView = (TextView)findViewById((R.id.email_valid_TW));

        numCreditEditText = (EditText)findViewById( R.id.numCreditEditText );
        numCreTextView=(TextView)findViewById(R.id.credit_card_valid_TW);

        addClientButton = (Button)findViewById( R.id.addClientButton );
        addClientButton.setEnabled(false);

        addClientButton.setOnClickListener(this);
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                checkOthers();
            }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern ptr = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
                String email = emailEditText.getText().toString();
                if (ptr.matcher(email).matches()||email=="")
                {
                    emailTextView.setText("");
                    e_mail=true;
                }
                else
                {
                    e_mail=false;
                    emailTextView.setText(email + " is " + "invalid");
                    emailTextView.setTextColor(Color.RED);
                }
            }
        });
        phoneNumEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                checkOthers();
            }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern ptrMobile05 = Pattern.compile("^[0][5][02348][0-9]{7}$",Pattern.CASE_INSENSITIVE);
                Pattern ptrMobile077 = Pattern.compile("^[0][7][7][0-9]{7}$",Pattern.CASE_INSENSITIVE);
                Pattern ptrTelephon = Pattern.compile("^[0][2-9][0-9]{7}$",Pattern.CASE_INSENSITIVE);
                String phone = phoneNumEditText.getText().toString();
                if (ptrMobile05.matcher(phone).matches()||ptrMobile077.matcher(phone).matches()||ptrTelephon.matcher(phone).matches() )
                {
                    phoneTextView.setText("");
                    phonen = true;
                }
                else
                {
                    phonen=false;
                    phoneTextView.setText(phone + " is an invalid phone number");
                    phoneTextView.setTextColor(Color.RED);
                }
            }
        });
        numCreditEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                checkOthers();
            }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern credinNum = Pattern.compile("^[0-9]{4}[-][0-9]{4}[-][0-9]{4}[-][0-9]{4}$");
                Pattern credinNum1 = Pattern.compile("^[0-9]{4}$");
                Pattern credinNum2 = Pattern.compile("^[0-9]{4}[-][0-9]{4}$");
                Pattern credinNum3 = Pattern.compile("^[0-9]{4}[-][0-9]{4}[-][0-9]{4}$");
                numCreTextView.setTextColor(Color.RED);

                String phone = numCreditEditText.getText().toString();
                if (credinNum.matcher(phone).matches())
                {
                    numCreTextView.setText("");
                    credit=true;
                }
                else if (credinNum1.matcher(phone).matches() ||credinNum2.matcher(phone).matches() ||credinNum3.matcher(phone).matches())
                {
                    credit=false;
                    numCreditEditText.setText("-"+phone);
                    numCreTextView.setText("You have to enter valid number like qwer-asdf-zxcv-ghkj");
                }
                else
                {
                    credit=false;
                    numCreTextView.setText(phone + " is an invalid credit card number");
                }
            }
        });
        fnameEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern pattern = Pattern.compile("^[ ].*$");
                valid_fname.setTextColor(Color.RED);

                if (pattern.matcher(fnameEditText.getText().toString()).matches()||fnameEditText.getText().toString().isEmpty())
                {
                    fname=false;
                    valid_fname.setText("please enter at least one letter as first name");
                }
                else
                {
                    fname = true;
                    valid_fname.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkOthers();
            }
        });
        lnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern pattern = Pattern.compile("^[ ].*$");
                valid_lname.setTextColor(Color.RED);

                if (pattern.matcher(lnameEditText.getText().toString()).matches()||lnameEditText.getText().toString().isEmpty())
                {
                    lname=false;
                    valid_lname.setText("please enter at least one letter as fast name");
                }
                else
                {
                    lname = true;
                    valid_lname.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkOthers();
            }
        });
        idEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern pattern = Pattern.compile("^[0-9]{9}$");
                idTextView.setTextColor(Color.RED);

                if (pattern.matcher(idEditText.getText().toString()).matches())
                {
                    id=true;
                    idTextView.setText("");

                }
                else
                {
                    id = false;
                    idTextView.setText("id must be nine digits");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkOthers();
            }
        });
    }

    private void checkOthers() {
        if (id&&phonen&&fname&&lname&&credit&&e_mail)
            addClientButton.setEnabled(true);
        else
            addClientButton.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        if ( v == addClientButton ) {
            addClient();
        }
    }

    private void addClient(){
        final ContentValues contentValues = new ContentValues();


            contentValues.put(TakeGoConst.ClientConst.FIRST_NAME,this.fnameEditText.getText().toString());
            contentValues.put(TakeGoConst.ClientConst.LAST_NAME,this.lnameEditText.getText().toString());
            contentValues.put(TakeGoConst.ClientConst.ID,Long.valueOf(this.idEditText.getText().toString()));
            contentValues.put(TakeGoConst.ClientConst.PHONE_NUMBER,this.phoneNumEditText.getText().toString());
            contentValues.put(TakeGoConst.ClientConst.EMAIL,this.emailEditText.getText().toString());
            contentValues.put(TakeGoConst.ClientConst.NUM_CREDIT,Long.valueOf(this.numCreditEditText.getText().toString()));


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
                        Toast.makeText(AddClientActivity.this, str, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else Toast.makeText(AddClientActivity.this, "This client is already exists!", Toast.LENGTH_SHORT).show();
                }
            }.execute();
    }

}

