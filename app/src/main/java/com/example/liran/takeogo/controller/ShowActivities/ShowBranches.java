package com.example.liran.takeogo.controller.ShowActivities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.controller.Adapters.BranchCursorAdapter;
import com.example.liran.takeogo.models.backend.DBManagerFactory;
import com.example.liran.takeogo.models.backend.IDBManager;

public class ShowBranches extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_branches);
        IDBManager db = DBManagerFactory.getMnager();
        ListView listView = (ListView) findViewById(R.id.branchListView);

        listView.setAdapter(new BranchCursorAdapter( ShowBranches.this ,db.getBranches(),0));
    }
}
