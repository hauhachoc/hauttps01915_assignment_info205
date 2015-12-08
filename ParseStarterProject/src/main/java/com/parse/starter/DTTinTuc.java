package com.parse.starter;

/**
 * Created by hau on 4/12/2015.
 */
public class DTTinTuc {
    String idtuade;
    String tuade;
    String ngaytao;
    String noidung;

    public DTTinTuc() {
        this.idtuade = null;
        this.tuade = null;
        this.ngaytao = null;
        this.noidung = null;
    }
    public DTTinTuc(String idtuade, String tuade, String ngaytao, String noidung) {
        this.idtuade = idtuade;
        this.tuade = tuade;
        this.ngaytao = ngaytao;
        this.noidung = noidung;
    }
}
