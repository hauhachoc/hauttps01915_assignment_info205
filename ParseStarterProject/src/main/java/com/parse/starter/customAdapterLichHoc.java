package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hau on 8/12/2015.
 */
public class customAdapterLichHoc extends ArrayAdapter<DTLichHoc> {

    public customAdapterLichHoc(Context context, ArrayList<DTLichHoc> DTLH) {
        super(context, R.layout.adapter_lichhoc, DTLH);
        // TODO Auto-generated constructor stub
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Activity context = (Activity)getContext();
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.adapter_lichhoc, null, true);
        TextView tvngay = (TextView)convertView.findViewById(R.id.tvngay);
        TextView tvgio = (TextView)convertView.findViewById(R.id.tvgio);
        TextView tvlop = (TextView)convertView.findViewById(R.id.tvlop);
        TextView tvphong = (TextView)convertView.findViewById(R.id.tvphong);

        DTLichHoc DTLH = getItem(position);

        tvngay.setText(DTLH.ngay);
        tvgio.setText(DTLH.gio);
        tvlop.setText(DTLH.lop);
        tvphong.setText(DTLH.phong);
        //
        return convertView;
    }
}