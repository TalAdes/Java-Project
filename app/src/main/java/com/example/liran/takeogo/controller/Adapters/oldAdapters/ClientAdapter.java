package com.example.liran.takeogo.controller.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.models.entities.Client;

import java.util.List;

/**
 * Created by טל on 28-Nov-17.
 */

public class ClientAdapter extends BaseAdapter {

    List<Client> clients;
    Context context;

    public ClientAdapter(List<Client> clients, Context context) {
        this.clients = clients;
        this.context = context;
    }

    @Override
    public int getCount() {
        return clients.size();
    }

    @Override
    public Object getItem(int position) {
        return clients.get(position);
    }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = View.inflate(context, R.layout.client_templte, null);

        TextView idTB = (TextView) convertView.findViewById(R.id.client_id_TB);
        TextView fnameTB = (TextView) convertView.findViewById(R.id.fname_TB);
        TextView lnameTB = (TextView) convertView.findViewById(R.id.lname_TB);
        TextView phoneTB = (TextView) convertView.findViewById(R.id.phoneNum_TB);
        TextView emailTB = (TextView) convertView.findViewById(R.id.email_TB);
        TextView creditTB = (TextView) convertView.findViewById(R.id.numCredit_TB);

        idTB.setText(((Long)clients.get(position).getId()).toString());
        fnameTB.setText(clients.get(position).getFname());
        lnameTB.setText(clients.get(position).getLname().toString());
        phoneTB.setText((clients.get(position).getPhoneNum()).toString());
        emailTB.setText(clients.get(position).getEmail());
        creditTB.setText(((Long)clients.get(position).getNumCredit()).toString());

        return convertView;    }
}
