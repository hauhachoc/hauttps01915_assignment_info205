package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TinTucChiTiet_Activity extends Activity {
    private DTTinTuc DTTT;
    private EditText edttuade, edtngaytao, edtnoidung;
    private Button btnXoaTinTuc, btnCapNhatTinTuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc_chi_tiet);
        khaibaoChiTietTin();
        capnhatTinTuc();
        xoaTinTuc();

    }
    public void khaibaoChiTietTin(){
        edttuade = (EditText) findViewById(R.id.editTexttuade);
        edtngaytao = (EditText) findViewById(R.id.editTextngaytao);
        edtnoidung = (EditText) findViewById(R.id.editTextnoidung);
        btnXoaTinTuc = (Button) findViewById(R.id.btnXoaTinTuc);
        btnCapNhatTinTuc = (Button) findViewById(R.id.buttonCapnhatTin);
        DTTT = new DTTinTuc();
        Intent intent = getIntent();
        DTTT.tuade = intent.getStringExtra("tuade");
        DTTT.ngaytao = intent.getStringExtra("ngaytao");
        DTTT.noidung = intent.getStringExtra("noidung");
        edttuade.setText(DTTT.tuade);
        edtngaytao.setText(DTTT.ngaytao);
        edtnoidung.setText(DTTT.noidung);
    }
    public void capnhatTinTuc(){
        btnCapNhatTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("TinTuc");
                query.whereEqualTo("tuade", DTTT.tuade);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(e==null){
                            object.put("tuade", edttuade.getText().toString());
                            object.put("ngaytao", edtngaytao.getText().toString());
                            object.put("noidung", edtnoidung.getText().toString());
                            object.saveInBackground();
                            Toast.makeText(TinTucChiTiet_Activity.this, "Cập nhật tài khoản thành công!",Toast.LENGTH_LONG).show();
                            finish();
                        }else {
                            Toast.makeText(TinTucChiTiet_Activity.this, "Lỗi !",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
    public void xoaTinTuc(){
        btnXoaTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("TinTuc");
                query.whereEqualTo("tuade", DTTT.tuade);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(e==null){
                            try {
                                object.delete();
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                                Toast.makeText(TinTucChiTiet_Activity.this, "Lỗi !",Toast.LENGTH_LONG).show();
                            }
                            object.saveInBackground();
                            Toast.makeText(TinTucChiTiet_Activity.this, "Xóa tin thành công!",Toast.LENGTH_LONG).show();
                            finish();
                        }else {
                            Toast.makeText(TinTucChiTiet_Activity.this, "Lỗi !",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }

}
