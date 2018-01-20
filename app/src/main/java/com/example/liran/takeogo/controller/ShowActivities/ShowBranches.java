package com.example.liran.takeogo.controller.ShowActivities;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import com.example.liran.takeogo.R;
import com.example.liran.takeogo.controller.Adapters.BranchCursorAdapter;
import com.example.liran.takeogo.controller.AddActivities.AddBranchActivity;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;

public class ShowBranches extends Activity {
    private ProgressBar progressBar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_branches);
        final IDBManager db = DBManagerFactory.getMnager();
        final ListView listView = (ListView) findViewById(R.id.branchListView);
        progressBar = (ProgressBar)findViewById(R.id.branchProgressBar);
        fab = (FloatingActionButton)findViewById(R.id.floatingActionButtonBrunch);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowBranches.this,AddBranchActivity.class));

            }
        });

        try {
            new AsyncTask<Void,Void,Cursor>(){
                @Override
                protected Cursor doInBackground(Void... voids) {
                    Cursor c;
                    try
                    {
                       db.dummyOperation();
                        c = db.getBranches();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(ShowBranches.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        return null;
                    }
                    return c;
                }
                @Override
                protected void onPostExecute(Cursor result) {
                    if(result == null)
                    {
                        Toast.makeText(ShowBranches.this,"There was an error to connect to internet",Toast.LENGTH_SHORT);
                        finish();
                    }
                    else {
                        progressBar.setVisibility(View.GONE);
                        listView.setAdapter(new BranchCursorAdapter(ShowBranches.this,result,0));
                        Toast.makeText(ShowBranches.this,"Connection to Companies DataBase Success",Toast.LENGTH_SHORT);
                    }
                }
            }.execute();
        }
        catch (Exception e) {Toast.makeText(ShowBranches.this, e.getMessage(), Toast.LENGTH_SHORT).show();}
    }
}
