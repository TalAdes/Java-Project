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


public class ClientCursorAdapter extends CursorAdapter {

    Cursor cursor;
    Context context;

    public ClientCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.cursor = c;
        this.context = context;    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.client_templte,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView idTB = (TextView) view.findViewById(R.id.client_id_TB1);
        TextView fnameTB = (TextView) view.findViewById(R.id.fname_TB1);
        TextView lnameTB = (TextView) view.findViewById(R.id.lname_TB1);
        TextView phoneTB = (TextView) view.findViewById(R.id.phoneNum_TB1);
        TextView emailTB = (TextView) view.findViewById(R.id.email_TB1);
        TextView creditTB = (TextView) view.findViewById(R.id.numCredit_TB1);


        idTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.ClientConst.ID)));
        fnameTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.ClientConst.FIRST_NAME)));
        lnameTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.ClientConst.LAST_NAME)));
        phoneTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.ClientConst.PHONE_NUMBER)));
        emailTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.ClientConst.EMAIL)));
        creditTB.setText(cursor.getString(cursor.getColumnIndexOrThrow(TakeGoConst.ClientConst.NUM_CREDIT)));
    }
}
