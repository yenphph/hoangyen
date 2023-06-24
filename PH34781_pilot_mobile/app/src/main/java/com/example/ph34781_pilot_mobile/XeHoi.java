package com.example.ph34781_pilot_mobile;

import java.io.Serializable;

public class XeHoi implements Serializable {
    private String ten, hangSX;
    private int namSX;
    private double giaBan;

    public XeHoi(String ten, String hangSX, int namSX, double giaBan) {
        this.ten = ten;
        this.hangSX = hangSX;
        this.namSX = namSX;
        this.giaBan = giaBan;
    }

    public XeHoi() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHangSX() {
        return hangSX;
    }

    public void setHangSX(String hangSX) {
        this.hangSX = hangSX;
    }

    public int getNamSX() {
        return namSX;
    }

    public void setNamSX(int namSX) {
        this.namSX = namSX;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
}
