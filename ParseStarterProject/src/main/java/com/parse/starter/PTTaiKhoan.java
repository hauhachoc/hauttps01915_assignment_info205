package com.parse.starter;

/**
 * Created by quocdat on 11/9/2015.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdat on 11/9/2015.
 */
public class PTTaiKhoan {
    private ParseObject oj;
    private ArrayList<DTTaiKhoan> listTK;
    private Context context;
    public  PTTaiKhoan(Context context){
        this.context = context;
    }

    public void TaoTaiKhoan(DTTaiKhoan DTTK){
        oj = new ParseObject("TaiKhoan");
        oj.put("tentaikhoan", DTTK.tentaikhoan);
        oj.put("matkhau", DTTK.matkhau);
        oj.put("hoten", DTTK.hoten);
        oj.put("sdt", DTTK.sdt);
        oj.put("email", DTTK.email);
        oj.saveInBackground();
    }
    public void capNhatTK(final String tentaikhoan, final String matkhau, final String hoten, final String sdt, final String email){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TaiKhoan");
        query.whereEqualTo("tentaikhoan", tentaikhoan);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, com.parse.ParseException e) {
                if (e == null) {
                    object.put("tentaikhoan", tentaikhoan);
                    object.put("matkhau", matkhau);
                    object.put("hoten", hoten);
                    object.put("sdt", sdt);
                    object.put("email", email);
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(context, "Cập nhật tài khoản thành công!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    public ArrayList<DTTaiKhoan> DanhSachTaiKhoan() {
        listTK = new ArrayList<DTTaiKhoan>();
        ParseQuery<ParseObject> parseTK = ParseQuery.getQuery("TaiKhoan");
        parseTK.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                listTK.clear();
                if (e == null) {
                    for (ParseObject post : objects) {
                        DTTaiKhoan DTTK = new DTTaiKhoan();
                        DTTK.tentaikhoan = post.getString("tentaikhoan");
                        DTTK.matkhau = post.getString("matkhau");
                        DTTK.hoten = post.getString("hoten");
                        DTTK.sdt = post.getString("sdt");
                        DTTK.email = post.getString("email");
                        listTK.add(DTTK);
                    }
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
        return listTK;
    }
}