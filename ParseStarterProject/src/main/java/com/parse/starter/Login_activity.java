package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by hau on 3/12/2015.
 */
public class Login_activity extends Activity {
    private EditText edtuser, edtpass;
    private Button btnLogin;
    private TextView tvTaoTaiKhoan;
    PTTaiKhoan PTDTTK;
    ArrayList<DTTaiKhoan> listDTTK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        khaibao();
        dangnhap();
        taotaikhoan();
    }
    public void khaibao(){
        edtuser = (EditText) findViewById(R.id.eduser);
        edtpass= (EditText) findViewById(R.id.edpass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvTaoTaiKhoan = (TextView) findViewById(R.id.tvTao);
        PTDTTK = new PTTaiKhoan(Login_activity.this);
        listDTTK = new ArrayList<>();
        listDTTK = PTDTTK.DanhSachTaiKhoan();
    }
    public void dangnhap(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtuser.getText().toString().equals("") || edtpass.getText().toString().equals("")){
                    edtuser.setError("Không bỏ trống ");
                    edtpass.setError("Không bỏ trống ");
                }else {
                    for (int i = 0; i < listDTTK.size(); i++) {
                        if (edtuser.getText().toString().equals(listDTTK.get(i).tentaikhoan)) {
                            if (edtpass.getText().toString().equals(listDTTK.get(i).matkhau)) {
                                Intent intent = new Intent(Login_activity.this, MainActivity.class);
                                intent.putExtra("tentaikhoan",listDTTK.get(i).tentaikhoan);
                                intent.putExtra("matkhau",listDTTK.get(i).matkhau);
                                intent.putExtra("hoten",listDTTK.get(i).hoten);
                                intent.putExtra("sdt",listDTTK.get(i).sdt);
                                intent.putExtra("email",listDTTK.get(i).email);
                                startActivity(intent);
                                finish();
                            } else {
                                edtpass.setError("Sai mật khẩu");
                                edtpass.requestFocus();
                            }
                        } else {
                            edtuser.setError("Sai tài khoản");
                            edtuser.requestFocus();
                        }
                    }
                    edtpass.setError("Sai mật khẩu");
                    edtpass.requestFocus();
                }
            }
        });
    }
    public void taotaikhoan(){
        tvTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_activity.this, TaoTaiKhoanActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
