package com.parse.starter;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hau on 4/12/2015.
 */
public class PTTinTuc {
    private ParseObject oj;
    private ArrayList<DTTinTuc> listTT;
    private Context context;
    public  PTTinTuc(Context context){
        this.context = context;
    }

    public void TaoTinTuc(DTTinTuc DTTT){
        oj = new ParseObject("TinTuc");
//        oj.put("idtuade", DTTT.idtuade);
        oj.put("tuade", DTTT.tuade);
        oj.put("ngaytao", DTTT.ngaytao);
        oj.put("noidung", DTTT.noidung);
        oj.saveInBackground();
    }
    public ArrayList<DTTinTuc> danhSachTinTuc(){
        listTT = new ArrayList<>();
        ParseQuery<ParseObject> parseTT = ParseQuery.getQuery("TinTuc");
        parseTT.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                listTT.clear();
                if (e == null) {
                    for (ParseObject post : objects) {
                        DTTinTuc DTTT = new DTTinTuc();
                        DTTT.idtuade = post.getObjectId();
                        DTTT.tuade = post.getString("tuade");
                        DTTT.ngaytao = post.getString("ngaytao");
                        DTTT.noidung = post.getString("noidung");
                        listTT.add(DTTT);
                    }
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
        return listTT;
    }
    public void xoaLoaiTinTuc(String ojIdLoaiTinTuc){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TinTuc");
        query.getInBackground(ojIdLoaiTinTuc, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, com.parse.ParseException e) {
                if (e == null) {
                    try {
                        object.delete();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    object.saveInBackground();
                }
            }
        });
    }
}
