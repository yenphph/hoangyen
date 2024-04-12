package com.example.navigation.Lan1;

public class Thit {
    String id;
    String name;
    int gia;
    int giamgia;
    int soluong;
    String loai;
    String img_url;

    public Thit(String id, String name, int gia, int giamgia, int soluong, String loai, String img_url) {
        this.id = id;
        this.name = name;
        this.gia = gia;
        this.giamgia = giamgia;
        this.soluong = soluong;
        this.loai = loai;
        this.img_url = img_url;
    }

    public Thit() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(int giamgia) {
        this.giamgia = giamgia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
