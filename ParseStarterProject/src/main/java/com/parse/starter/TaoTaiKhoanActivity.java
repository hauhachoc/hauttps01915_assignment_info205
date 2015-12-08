package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hau on 4/12/2015.
 */
public class TaoTaiKhoanActivity extends Activity {
    private PTTaiKhoan PTDTTK;
    private ArrayList<DTTaiKhoan> listDTTK;
    private DTTaiKhoan DTTK;
    private EditText tentaikhoan, matkhau, nhaplaimatkhau, hoten, sdt, email;
    private Button taotaikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taotaikhoan);
        khaibao();
        taoTaiKhoan();
    }
    public void khaibao(){
        tentaikhoan = (EditText) findViewById(R.id.tentaikhoan);
        matkhau = (EditText) findViewById(R.id.matkhau);
        nhaplaimatkhau = (EditText) findViewById(R.id.nhaplaimatkhau);
        hoten = (EditText) findViewById(R.id.hoten);
        sdt = (EditText) findViewById(R.id.sdt);
        email = (EditText) findViewById(R.id.email);
        taotaikhoan = (Button) findViewById(R.id.dangki);
        PTDTTK = new PTTaiKhoan(TaoTaiKhoanActivity.this);
        listDTTK = new ArrayList<>();
        listDTTK = PTDTTK.DanhSachTaiKhoan();
    }
    public void taoTaiKhoan(){
        taotaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tentaikhoan.getText().toString().equals("") || matkhau.getText().toString().equals("") || nhaplaimatkhau.getText().toString().equals("")){
                    tentaikhoan.setError("Không bỏ trống");
                    matkhau.setError("Không bỏ trống");
                    nhaplaimatkhau.setError("Không bỏ trống");
                }else {
                    if (matkhau.getText().toString().equals(nhaplaimatkhau.getText().toString())) {
                        DTTK = new DTTaiKhoan();
                        DTTK.tentaikhoan = tentaikhoan.getText().toString();
                        DTTK.matkhau = matkhau.getText().toString();
                        DTTK.hoten = hoten.getText().toString();
                        DTTK.sdt = sdt.getText().toString();
                        DTTK.email = email.getText().toString();
                        PTDTTK.TaoTaiKhoan(DTTK);
                        Toast.makeText(TaoTaiKhoanActivity.this, "Đã tạo tài khoản thành công", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(TaoTaiKhoanActivity.this, Login_activity.class);
                        startActivity(i);
                        finish();
                    } else {
                        matkhau.setError("Không trùng");
                        nhaplaimatkhau.setError("Không trùng");
                    }
                }
            }
        });
    }
}