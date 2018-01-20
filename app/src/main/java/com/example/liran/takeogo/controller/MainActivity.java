package com.example.liran.takeogo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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

    private ImageButton showCarButton;
    private ImageButton showClientButton;
    private ImageButton showModelCarButton;
    private ImageButton showBrunchButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {
        showBrunchButton = (ImageButton)findViewById( R.id.buttonbrunches );
        showClientButton = (ImageButton)findViewById( R.id.buttonClient );
        showCarButton = (ImageButton)findViewById( R.id.carButton );
        showModelCarButton = (ImageButton)findViewById( R.id.carModelButton );


        showBrunchButton.setOnClickListener( this );
        showClientButton.setOnClickListener( this );
        showCarButton.setOnClickListener( this );
        showModelCarButton.setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {
        if ( v == showCarButton )   showAllCarButton();
        else if ( v == showModelCarButton )  showAllModelCarButton();
        else if ( v == showClientButton ) showAllClientButton();
        else if ( v == showBrunchButton ) showAllBranchButton();
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

}
