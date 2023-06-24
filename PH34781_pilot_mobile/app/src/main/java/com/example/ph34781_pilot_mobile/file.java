package com.example.ph34781_pilot_mobile;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class file {
    public static void writeFile(Context context, String filename, Object object){
        try{
            FileOutputStream ois = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream fos = new ObjectOutputStream(ois);
            fos.writeObject(object);
            fos.close();
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static  Object readFile(Context context, String filename){
        Object list = null;
        try{
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream is = new ObjectInputStream(fis);
            list = (List) is.readObject();
            is.close();
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
