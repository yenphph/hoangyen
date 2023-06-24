package com.example.ph34781_pilot_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
private Context context = MainActivity3.this;
ArrayList<XeHoi> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText txtten = findViewById(R.id.txttena);
        EditText txthang = findViewById(R.id.txthanga);
        EditText txtnam = findViewById(R.id.txtnama);
        EditText txtgia = findViewById(R.id.txtgiaa);

        ArrayList<XeHoi> readds = new ArrayList<>();
        readds.clear();
        readds = (ArrayList<XeHoi>) file.readFile(context, "file.txt");
        list.addAll(readds);

        Button btnadd = findViewById(R.id.btnadda);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(txtten.getText().toString().isEmpty()){
                        Toast.makeText(context, "Chưa nhập tên", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(txthang.getText().toString().isEmpty()){
                        Toast.makeText(context, "Chưa nhập hãng", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(txtnam.getText().toString().isEmpty()){
                        Toast.makeText(context, "Chưa nhập năm", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(txtgia.getText().toString().isEmpty()){
                        Toast.makeText(context, "Chưa nhập giá", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String ten = txtten.getText().toString();
                    String hang = txthang.getText().toString();
                    int nam = Integer.parseInt(txtnam.getText().toString());
                    double gia = Double.parseDouble(txtgia.getText().toString());

                    if(nam <= 1980 && nam >= 2023){
                        Toast.makeText(context, "Năm sản xuất phải từ 1980 đến 2023", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(gia < 0){
                        Toast.makeText(context, "Giá phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    }
                    list.add(new XeHoi(ten, hang, nam, gia));
                    file.writeFile(context, "file.txt", list);
                    startActivity(new Intent(context, MainActivity2.class));
                    finish();
                }catch (NumberFormatException e){
                    Toast.makeText(context, "Nhập sai dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}