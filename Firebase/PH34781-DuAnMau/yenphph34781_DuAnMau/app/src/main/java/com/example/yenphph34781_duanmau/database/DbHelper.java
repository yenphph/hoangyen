package com.example.yenphph34781_duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_name="QTT";
    public DbHelper(@Nullable Context context) {
        super(context, DB_name, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String db_thuthu="create table thuthu(matt text primary key," +
                "hoten text," +
                "matkhau text," +
                "loaitaikhoan text)";
        db.execSQL(db_thuthu);
        String db_thanhvien="create table thanhvien(matv integer primary key autoincrement," +
                "hoten text," +
                "namsinh text)";
        db.execSQL(db_thanhvien);
        String db_Loai="create table loaisach(maloai integer primary key autoincrement," +
                "tenloai text)";
        db.execSQL(db_Loai);
        String db_sach="create table sach(masach integer primary key autoincrement," +
                "tensach text," +
                "giathue integer," +
                "maloai integer references loaisach(maloai))";//cho no chinh xac
        db.execSQL(db_sach);
        String db_phieumuon="create table phieumuon(mapm integer primary key autoincrement," +
                "matv integer references thanhvien(matv)," +
                "matt text references thuthu(matt)," +
                "masach integer references sach(masach)," +
                "ngay text," +
                "trasach integer," +
                "tienthue integer)";
        db.execSQL(db_phieumuon);

        db.execSQL("INSERT INTO loaisach VALUES (1, 'Âm nhạc'),(2,'Công nghệ thông tin'),(3, 'Cuộc sống'), (4, 'Tâm lý học')");
        db.execSQL("INSERT INTO sach VALUES (1, 'Vượt qua bản ngã', 2500, 3), (2, 'Java', 1000, 2), (3, 'Tuổi trẻ băn khoăn', 2000, 4)");
        db.execSQL("INSERT INTO thuthu VALUES ('tt1','Hoang Anh','a','admin'),('tt2','Linh Trang','tt2','thuthu')");
        db.execSQL("INSERT INTO thanhvien VALUES (1,'Ngọc Minh','2004'),(2,'Thu Anh','2004')");
        db.execSQL("INSERT INTO phieumuon VALUES (1,1,'tt1', 1, '12/05/2023', 1, 2003),(2,1,'tt1', 3, '11/05/2023', 0, 2003),(3,2,'tt2', 1, '01/05/2023', 1, 2003)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion){
            db.execSQL("drop table if exists loaisach");
            db.execSQL("drop table if exists sach");
            db.execSQL("drop table if exists thuthu");
            db.execSQL("drop table if exists thanhvien");
            db.execSQL("drop table if exists phieumuon");
            onCreate(db);
        }
    }
}
