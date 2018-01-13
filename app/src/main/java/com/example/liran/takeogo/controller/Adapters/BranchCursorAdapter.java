package com.example.liran.takeogo.controller.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liran.takeogo.R;
import com.example.liran.takeogo.models.backend.TakeGoConst;

/**
 * Created by טל on 11-Dec-17.
 */

public class BranchCursorAdapter extends CursorAdapter {

    Cursor cursor;
    Context context;
    String url;


    public BranchCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.cursor = cursor;
        this.context = context;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.branch_template,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView idTB = (TextView) view.findViewById(R.id.id_TB);
        TextView parkingTB = (TextView) view.findViewById(R.id.numParking_TB);
        TextView cityTB = (TextView) view.findViewById(R.id.city_TB);
        TextView streetTB = (TextView) view.findViewById(R.id.street_TB);
        TextView apartmentTB = (TextView) view.findViewById(R.id.apartmentNum_TB);
        ImageView imageB = (ImageView) view.findViewById(R.id.branchImage);
        url = "http://tades.vlab.jct.ac.il/";



        idTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.BranchConst.ID)));
        parkingTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.BranchConst.NUMBER_PARKING)));
        cityTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.BranchConst.CITY)));
        streetTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.BranchConst.STREET)));
        apartmentTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.BranchConst.NUM_APARTMENT)));
        url = url + cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.BranchConst.IMAGE));
        Glide.with(this.context).load(url).into(imageB);

    }
}
