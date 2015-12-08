package com.parse.starter;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hau on 4/12/2015.
 */
public class PTLichHoc {
    private ParseObject oj;
    private ArrayList<DTLichHoc> listLH;
    private Context context;
    public  PTLichHoc(Context context){
        this.context = context;
    }

    public void TaoLichHoc(DTLichHoc DTLH){
        oj = new ParseObject("LichHoc");
        oj.put("ngay", DTLH.ngay);
        oj.put("gio", DTLH.gio);
        oj.put("lop", DTLH.lop);
        oj.put("phong", DTLH.phong);
        oj.put("ghichu", DTLH.ghichu);
        oj.saveInBackground();
    }
    public ArrayList<DTLichHoc> danhSachLichHoc(){
        listLH = new ArrayList<>();
        ParseQuery<ParseObject> parseLH = ParseQuery.getQuery("LichHoc");
        parseLH.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                listLH.clear();
                if (e == null) {
                    for (ParseObject post : objects) {
                        DTLichHoc DTLH = new DTLichHoc();
                        DTLH.idlichhoc = post.getObjectId();
                        DTLH.ngay = post.getString("ngay");
                        DTLH.gio = post.getString("gio");
                        DTLH.lop = post.getString("lop");
                        DTLH.ghichu = post.getString("ghichu");
                        listLH.add(DTLH);
                    }
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
        return listLH;
    }
}
