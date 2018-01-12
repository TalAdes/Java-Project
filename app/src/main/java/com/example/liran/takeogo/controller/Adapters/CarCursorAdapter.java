package com.example.liran.takeogo.controller.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.models.backend.TakeGoConst;

/**
 * Created by טל on 11-Jan-18.
 */

public class CarCursorAdapter extends CursorAdapter {
    Cursor cursor;
    Context context;
    public CarCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.cursor = cursor;
        this.context = context;    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.car_template,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView carIdTB = (TextView) view.findViewById(R.id.CarID_TB);
        TextView modelIdTB = (TextView) view.findViewById(R.id.modelId_TB);
        TextView modelNameTB = (TextView) view.findViewById(R.id.modelName_TB);
        TextView kilometer = (TextView) view.findViewById(R.id.kilometer_TB);
        TextView branchTB = (TextView) view.findViewById(R.id.BranchTB);

        carIdTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.CarConst.ID_CAR)));
        modelIdTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.CarConst.ID_TYPE_MODEL)));
        modelNameTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.CarConst.MODEL_NAME)));
        kilometer.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.CarConst.KILLOMETER)));
        branchTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.CarConst.ID_BRANCH)));
    }
}
