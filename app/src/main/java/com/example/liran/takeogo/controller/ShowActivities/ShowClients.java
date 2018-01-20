package com.example.liran.takeogo.controller.ShowActivities;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.controller.Adapters.ClientCursorAdapter;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;

public class ShowClients extends Activity {

    private ProgressBar progressBar;
<<<<<<< HEAD

=======
    private FloatingActionButton fab;
>>>>>>> 7db9d49dd76ec0e142a35c594bfee318ba1aee1d
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clients);
        final IDBManager db = DBManagerFactory.getMnager();
        final ListView listView = (ListView) findViewById(R.id.clientListView);
        fab = (FloatingActionButton)findViewById(R.id.floatingActionButtonClient);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowClients.this,AddClientActivity.class));

            }
        });
        progressBar = (ProgressBar)findViewById(R.id.clientProgressBar);
        try {
            new AsyncTask<Void,Void,Cursor>(){
                @Override
                protected Cursor doInBackground(Void... voids) {
                    Cursor c;
                    try
                    {
                        //db.dummyOperation();
                        c = db.getClients();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(ShowClients.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        return null;
                    }
                    return c;
                }
                @Override
                protected void onPostExecute(Cursor result) {
                    if(result==null)
                    {
                        Toast.makeText(ShowClients.this,"There was an error to connect to internet",Toast.LENGTH_SHORT);
                        finish();
                    }

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ShowClients.this,"Connection to DataBase Success",Toast.LENGTH_SHORT);
                    listView.setAdapter(new ClientCursorAdapter(ShowClients.this,result,0 ));
                    Toast.makeText(ShowClients.this,"succes :)",Toast.LENGTH_SHORT);
                }
            }.execute();
        }
        catch (Exception e) {Toast.makeText(this,"fail :(",Toast.LENGTH_SHORT);}
    }
}
