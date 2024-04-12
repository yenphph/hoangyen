package com.example.luyentap2.model;

import java.util.HashMap;

public class SanPham {
    String id;
    String img_url;
    String tenSaPham;
    String ngayRaMat;
    int gia;
    int soLuong;
    String tinhtrang;

    public SanPham(String id, String img_url, String tenSaPham, String ngayRaMat, int gia, int soLuong, String tinhtrang) {
        this.id = id;
        this.img_url = img_url;
        this.tenSaPham = tenSaPham;
        this.ngayRaMat = ngayRaMat;
        this.gia = gia;
        this.soLuong = soLuong;
        this.tinhtrang = tinhtrang;
    }

    public SanPham() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTenSaPham() {
        return tenSaPham;
    }

    public void setTenSaPham(String tenSaPham) {
        this.tenSaPham = tenSaPham;
    }

    public String getNgayRaMat() {
        return ngayRaMat;
    }

    public void setNgayRaMat(String ngayRaMat) {
        this.ngayRaMat = ngayRaMat;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
    //this.id = id;
    //        this.img_url = img_url;
    //        this.tenSaPham = tenSaPham;
    //        this.ngayRaMat = ngayRaMat;
    //        this.gia = gia;
    //        this.soLuong = soLuong;
    //        this.tinhtrang = tinhtrang;
    public HashMap<String, Object> hmSanPham(){
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", this.id);
        data.put("img_url", this.img_url);
        data.put("tensanpham", this.tenSaPham);
        data.put("gia", this.gia);
        data.put("soluong", this.soLuong);
        data.put("tinhtrang", this.tinhtrang);
        return data;
    }
}
