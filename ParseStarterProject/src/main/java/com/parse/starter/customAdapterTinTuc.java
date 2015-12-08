package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Created by quocdat on 11/29/2015.
 */
public class customAdapterTinTuc extends ArrayAdapter<DTTinTuc> {

    public customAdapterTinTuc(Context context, ArrayList<DTTinTuc> DTTT) {
        super(context, R.layout.adapter_tintuc, DTTT);
        // TODO Auto-generated constructor stub
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Activity context = (Activity)getContext();
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.adapter_tintuc, null, true);
        TextView tvtuade = (TextView)convertView.findViewById(R.id.tvtuade);
        TextView tvngaytao = (TextView)convertView.findViewById(R.id.tvngaytao);
        TextView tvnoidung = (TextView)convertView.findViewById(R.id.tvnoidung);

        DTTinTuc DTTT = getItem(position);

        tvtuade.setText(DTTT.tuade);
        tvngaytao.setText(DTTT.ngaytao);
        tvnoidung.setText(DTTT.noidung);
        //
        return convertView;
    }
}
