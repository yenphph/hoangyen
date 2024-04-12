package com.example.yenphph34781_duanmau.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yenphph34781_duanmau.database.DbHelper;
import com.example.yenphph34781_duanmau.model.LoaiSach;
import com.example.yenphph34781_duanmau.model.Sach;

import java.util.ArrayList;

public class SachDao {
    private final DbHelper dbHelper;

    public SachDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Sach> getDSSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from sach", null);//cai cu
        Cursor cursor = db.rawQuery("select s.masach, s.tensach, s.giathue, s.maloai, l.tenloai from sach s, loaisach l where s.maloai =l.maloai", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3), cursor.getString(4)));
                cursor.moveToNext();
            }
        }
        return list;
    }

    public  boolean addSach(String tensach, int giatien, int maloai){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensach", tensach);
        values.put("giathue", giatien);
        values.put("maloai", maloai);
        long kt =db.insert("sach", null, values);
        return (kt>0);
    }                   // can int masach de ma biet minh cap nhat ma sach nao
    public  boolean UpSach(int masach, String tensach, int giatien, int maloai) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from sach where masach = ?", new String[]{String.valueOf(masach)});
        if (cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("tensach", tensach);
            values.put("giathue", giatien);
            values.put("maloai", maloai);
            long kt = db.update("sach", values, "masach=?", new String[]{String.valueOf(masach)});
            return (kt > 0);
        }
        return false;
    }
    public boolean deletesach(int masach){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from sach where masach= ? ", new String[]{String.valueOf(masach)});
        if(cursor.getCount() > 0){
            long kt = db.delete("sach", "masach = ?", new String[]{String.valueOf(masach)});
            return true;
        }
        return false;
    }

}
