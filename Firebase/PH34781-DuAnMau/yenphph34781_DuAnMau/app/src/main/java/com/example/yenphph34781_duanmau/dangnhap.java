package com.example.yenphph34781_duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yenphph34781_duanmau.dao.ThuThuDao;

public class dangnhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        EditText txtuser = findViewById(R.id.txtuser);
        EditText txtpass = findViewById(R.id.txtpass);
        Button btndn = findViewById(R.id.btnlogin);
        ThuThuDao dao = new ThuThuDao(this);
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtuser.getText().toString();
                String pass = txtpass.getText().toString();
                if(dao.login(user, pass)){
                    startActivity(new Intent(dangnhap.this, MainActivity.class));
                }else{
                    Toast.makeText(dangnhap.this, "Nhap sai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView txt = findViewById(R.id.vit);
        Glide.with(this).load(R.drawable.giphy).into(txt);
        //with o dau, load hinh nao, into o dau

    }
}