package com.example.liran.takeogo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.controller.AddActivities.AddBranchActivity;
import com.example.liran.takeogo.controller.AddActivities.AddCarActivity;
import com.example.liran.takeogo.controller.AddActivities.AddCarModel;
import com.example.liran.takeogo.controller.AddActivities.AddClientActivity;
import com.example.liran.takeogo.controller.ShowActivities.ShowBranches;
import com.example.liran.takeogo.controller.ShowActivities.ShowCar;
import com.example.liran.takeogo.controller.ShowActivities.ShowCarModels;
import com.example.liran.takeogo.controller.ShowActivities.ShowClients;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addCarButton;
    private Button showAllCarButton;
    private Button addModelCarButton;
    private Button showAllModelCarButton;
    private Button addClientButton;
    private Button showAllClientButton;
    private Button showAllBranchButton;
    private Button addBranchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {
        addCarButton = (Button)findViewById( R.id.addCarButton );
        showAllCarButton = (Button)findViewById( R.id.showAllCarButton );
        addModelCarButton = (Button)findViewById( R.id.addModelCarButton );
        showAllModelCarButton = (Button)findViewById( R.id.showAllModelCarButton );
        addClientButton = (Button)findViewById( R.id.addClientButton );
        showAllClientButton = (Button)findViewById( R.id.showAllClientButton );
        showAllBranchButton = (Button)findViewById( R.id.showAllBranchButton );
        addBranchButton = (Button)findViewById( R.id.addBranchButton );

        addCarButton.setOnClickListener( this );
        showAllCarButton.setOnClickListener( this );
        addModelCarButton.setOnClickListener( this );
        showAllModelCarButton.setOnClickListener( this );
        addClientButton.setOnClickListener( this );
        showAllClientButton.setOnClickListener( this );
        showAllBranchButton.setOnClickListener( this );
        addBranchButton.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == addCarButton )       addCarButton();
        else if ( v == addModelCarButton )  addCarModelButton();
        else if ( v == addClientButton )    addClientButton();
        else if ( v == addBranchButton )    addBranchButton();
        else if ( v == showAllCarButton )       showAllCarButton();
        else if ( v == showAllModelCarButton )  showAllModelCarButton();
        else if ( v == showAllClientButton ) showAllClientButton();
        else if ( v == showAllBranchButton ) showAllBranchButton();
    }


    private void showAllCarButton() {
        Intent intent = new Intent(this,ShowCar.class);
        startActivity(intent);
    }
    private void showAllBranchButton() {
        Intent intent = new Intent(this,ShowBranches.class);
        startActivity(intent);
    }
    private void showAllClientButton() {
        Intent intent = new Intent(this,ShowClients.class);
        startActivity(intent);
    }
    private void showAllModelCarButton() {
        Intent intent = new Intent(this,ShowCarModels.class);
        startActivity(intent);
    }

    private void addCarModelButton() {
        Intent intent = new Intent(this,AddCarModel.class);
        startActivity(intent);
    }
    private  void addCarButton(){
        Intent intent = new Intent(this, AddCarActivity.class);
        startActivity(intent);
    }
    private  void addBranchButton(){
        Intent intent = new Intent(this, AddBranchActivity.class);
        startActivity(intent);
    }
    private  void addClientButton(){
        Intent intent = new Intent(this,AddClientActivity.class);
        startActivity(intent);
    }


}
