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
 * Created by טל on 14-Dec-17.
 */

public class CarModelCursorAdapter extends CursorAdapter {
    Cursor cursor;
    Context context;

    public CarModelCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.cursor = cursor;
        this.context = context;    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.car_model_template,parent,false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView modelDTB = (TextView) view.findViewById(R.id.modelD_TB);
        TextView companyNameTB = (TextView) view.findViewById(R.id.companyName_TB);
        TextView engineCapacityTB = (TextView) view.findViewById(R.id.engineCapacity_TB);
        TextView gearboxTB = (TextView) view.findViewById(R.id.gearbox_TB);
        TextView numberOfSeatsTB = (TextView) view.findViewById(R.id.numberOfSeats_TB);
        TextView modelNameTB = (TextView) view.findViewById(R.id.modelName_TB);

        modelDTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.CarModelConst.ID)));
        companyNameTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.CarModelConst.NAM_COMP)));
        engineCapacityTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.CarModelConst.ENGINE_CAP)));
        gearboxTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.CarModelConst.GEERBOX)));
        numberOfSeatsTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.CarModelConst.NUMBER_OF_SEATS)));
        modelNameTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.CarModelConst.NAME)));
    }
}
