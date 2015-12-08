package com.parse.starter;

/**
 * Created by quocdat on 11/9/2015.
 */
public class DTTaiKhoan {
    String tentaikhoan;
    String matkhau;
    String hoten;
    String sdt;
    String email;

    public DTTaiKhoan() {
        this.tentaikhoan = null;
        this.matkhau = null;
        this.hoten = null;
        this.sdt = null;
        this.email = null;
    }
    public DTTaiKhoan(String tentaikhoan, String matkhau, String hoten, String sdt, String email) {
        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.sdt = sdt;
        this.email = email;
    }
}
