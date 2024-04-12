package com.example.navigation.activity1;

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

import com.example.navigation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class dangky1 extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky1);
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        EditText txtemail = findViewById(R.id.txtemail_K);
        EditText txtpassword = findViewById(R.id.txtpassword_K);
        EditText txthoten = findViewById(R.id.txthoten_K);
        EditText txtnamsinh = findViewById(R.id.txtnamsinh_K);
        EditText txtchucvu = findViewById(R.id.txtchucvu_K);

        Button dangky = findViewById(R.id.btndangky_K);
        TextView dangnhap = findViewById(R.id.dangnhap);

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtemail.getText().toString();
                String password = txtpassword.getText().toString();
                String fullname = txthoten.getText().toString();
                String yearOfBirth = txtnamsinh.getText().toString();
                String duty = txtchucvu.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(fullname) && !TextUtils.isEmpty(yearOfBirth) && !TextUtils.isEmpty(duty)){
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        String userId = auth.getCurrentUser().getUid();
                                        Map<String, Object> account = new HashMap<>();
                                        account.put("email", email);
                                        account.put("fullname", fullname);
                                        account.put("yearOfBirth", yearOfBirth);
                                        account.put("duty", duty);
                                        firebaseFirestore.collection("TaiKhoan")
                                                .document(userId).set(account).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        txtemail.setText("");
                                                        txtpassword.setText("");
                                                        txthoten.setText("");
                                                        txtnamsinh.setText("");
                                                        txtchucvu.setText("");
                                                        Toast.makeText(dangky1.this, "Luu du lieu thanh cong", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(dangky1.this, "Luu du lieu that bai", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(dangky1.this, "Loi khi luu du lieu vao firestore", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }else{
                                        Toast.makeText(dangky1.this, "Dang ky that bai", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(dangky1.this, "Vui long dien day du thong tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dangky1.this, dangnhap1.class));
            }
        });
    }
}