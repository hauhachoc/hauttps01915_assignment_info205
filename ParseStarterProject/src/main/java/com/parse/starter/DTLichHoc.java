package com.parse.starter;

/**
 * Created by hau on 4/12/2015.
 */
public class DTLichHoc {
    String idlichhoc;
    String ngay;
    String gio;
    String lop;
    String phong;
    String ghichu;
    public DTLichHoc() {
        this.idlichhoc = null;
        this.ngay = null;
        this.gio = null;
        this.lop = null;
        this.phong = null;
        this.ghichu = null;
    }
    public DTLichHoc(String idlichhoc,String ngay, String gio, String lop, String phong, String ghichu) {
        this.idlichhoc = idlichhoc;
        this.ngay = ngay;
        this.gio = gio;
        this.lop = lop;
        this.phong = phong;
        this.ghichu = ghichu;
    }
}
