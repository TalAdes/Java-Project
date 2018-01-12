package com.example.liran.takeogo.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liran.takeogo.R;

public class LoginActivity extends Activity implements View.OnClickListener {
    private EditText userText;
    private EditText passwordText;
    private Button logInButton;
    private SharedPreferences sharedPreferences;


    private void findViews() {
        userText = (EditText)findViewById( R.id.userText );
        passwordText = (EditText)findViewById( R.id.passwordText );
        logInButton = (Button)findViewById( R.id.logInButton );
        logInButton.setOnClickListener( this );

         sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString("Liran","Liran");
        editor.putInt("12345",12345);
        editor.putString("Tal","Tal");
        editor.putBoolean("save",true);
        editor.commit();

    }

    @Override
    public void onClick(View v) {
        if ( v == logInButton ) {
            login();
        }

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }

    private void login()
    {
        String user = userText.getText().toString();
        String password = passwordText.getText().toString();
        if(sharedPreferences.contains(user)&&sharedPreferences.contains(password))
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getBaseContext(),"Login failed",Toast.LENGTH_LONG).show();
        }

        }

}
