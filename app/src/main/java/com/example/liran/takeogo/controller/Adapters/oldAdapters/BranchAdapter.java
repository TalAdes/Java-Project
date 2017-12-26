package com.example.liran.takeogo.controller.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.models.entities.Branch;

import java.util.List;

/**
 * Created by טל on 28-Nov-17.
 */

public class BranchAdapter extends BaseAdapter {

    List<Branch> branchesList;
    Context context;

    public BranchAdapter(List<Branch> branchesList, Context context) {
        this.branchesList = branchesList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return branchesList.size();
    }

    @Override
    public Object getItem(int position) {
        return branchesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = View.inflate(context, R.layout.branch_template, null);

        TextView idTB = (TextView) convertView.findViewById(R.id.id_TB);
        TextView parkingTB = (TextView) convertView.findViewById(R.id.numParking_TB);
        TextView cityTB = (TextView) convertView.findViewById(R.id.city_TB);
        TextView streetTB = (TextView) convertView.findViewById(R.id.street_TB);
        TextView apartmentTB = (TextView) convertView.findViewById(R.id.apartmentNum_TB);

        idTB.setText(((Long)branchesList.get(position).getIdBranch()).toString());
        parkingTB.setText(((Integer)branchesList.get(position).getNumParking()).toString());
        cityTB.setText(branchesList.get(position).getCity().toString());
        streetTB.setText((branchesList.get(position).getStreet()).toString());
        apartmentTB.setText(((Integer)branchesList.get(position).getNumApart()).toString());

        return convertView;
    }
}
