package com.example.luyentap2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class dangky extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        EditText txtemail = findViewById(R.id.txtemail_K);
        EditText txtmatKhau = findViewById(R.id.txtmatkhau_K);
        EditText txthoTen = findViewById(R.id.txthoten_K);
        EditText txtsdt = findViewById(R.id.txtsdt_K);
        EditText txtdiaChiNha = findViewById(R.id.txtdiachinha_K);

        Button dangKy = findViewById(R.id.btnsingup_K);
        TextView dangNhap = findViewById(R.id.dangnhap);

        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtemail.getText().toString();
                String matKhau = txtmatKhau.getText().toString();
                String hoTen = txthoTen.getText().toString();
                String sdt = txtsdt.getText().toString();
                String diaChiNha = txtdiaChiNha.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(matKhau) && !TextUtils.isEmpty(hoTen) && !TextUtils.isEmpty(sdt) && !TextUtils.isEmpty(diaChiNha)) {
                    auth.createUserWithEmailAndPassword(email, matKhau)
                            .addOnCompleteListener(dangky.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String idTaiKhoan = auth.getCurrentUser().getUid();

                                        Map<String, Object> khachHang = new HashMap<>();
                                        khachHang.put("idKhachHang", idTaiKhoan);
                                        khachHang.put("email", email);
                                        khachHang.put("hoTen", hoTen);
                                        khachHang.put("sdt", sdt);
                                        khachHang.put("diaChiNha", diaChiNha);

                                        firebaseFirestore.collection("KhachHang").document(idTaiKhoan)
                                                .set(khachHang)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(dangky.this, "Lưu dữ liệu khách hàng thành công", Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(dangky.this, "Lưu dữ liệu tài khoản khách hàng thất bại", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(dangky.this, "Đăng ký tài khoản khách hàng thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(dangky.this, "Điền đầy đủ thông tin khách hàng", Toast.LENGTH_SHORT).show();
                }

            }
        });

        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dangky.this, dangnhap.class));
            }
        });
    }
}
