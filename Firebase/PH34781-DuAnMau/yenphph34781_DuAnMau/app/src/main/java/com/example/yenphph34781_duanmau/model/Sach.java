package com.example.yenphph34781_duanmau.model;

public class Sach {
    private int masach;
    private String tensach;
    private int giathue;
    private int maloai;
    private String tenloai;
    private int soluongmuon;

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
//    public Sach(int masach, String tensach, int giathue, int maloai) {
//        this.masach = masach;
//        this.tensach = tensach;
//        this.giathue = giathue;
//        this.maloai = maloai;
//    }

//    public Sach(int masach, String tensach, int giathue, String tenloai) {
//        this.masach = masach;
//        this.tensach = tensach;
//        this.giathue = giathue;
//
//        this.tenloai = tenloai;
//    }

    public int getSoluongmuon() {
        return soluongmuon;
    }

    public void setSoluongmuon(int soluongmuon) {
        this.soluongmuon = soluongmuon;
    }

    public Sach(int masach, String tensach, int giathue, int maloai, String tenloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public Sach(int masach, String tensach,int soluongmuon) {
        this.masach = masach;
        this.tensach = tensach;
        this.soluongmuon = soluongmuon;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }
}
