package com.example.liran.takeogo.controller.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.liran.takeogo.R;
import com.example.liran.takeogo.models.entities.CarModel;

import java.util.List;

/**
 * Created by טל on 27-Nov-17.
 */

public class CarModelAdapter extends BaseAdapter {

    List<CarModel> carModelList;
    Context context;

    public CarModelAdapter(List<CarModel> carModelList, Context context) {
        this.carModelList = carModelList;
        this.context =context;
    }

    @Override
    public int getCount() {
        return carModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return carModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = View.inflate(context, R.layout.car_model_template, null);

        TextView modelDTB = (TextView) convertView.findViewById(R.id.modelD_TB);
        TextView companyNameTB = (TextView) convertView.findViewById(R.id.companyName_TB);
        TextView engineCapacityTB = (TextView) convertView.findViewById(R.id.engineCapacity_TB);
        TextView gearboxTB = (TextView) convertView.findViewById(R.id.gearbox_TB);
        TextView numberOfSeatsTB = (TextView) convertView.findViewById(R.id.numberOfSeats_TB);
        TextView modelNameTB = (TextView) convertView.findViewById(R.id.modelName_TB);

        modelDTB.setText(((Long)carModelList.get(position).getIdModel()).toString());
        companyNameTB.setText(carModelList.get(position).getNameComp());
        engineCapacityTB.setText(((Integer)carModelList.get(position).getEngineCap()).toString());
        gearboxTB.setText(carModelList.get(position).getGeerbox().toString());
        numberOfSeatsTB.setText(((Integer) carModelList.get(position).getNumberOfSeats()).toString());
        modelNameTB.setText(carModelList.get(position).getNameModel());

        return convertView;
    }
}
