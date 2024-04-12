package com.example.luyentap;

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

public class SignUpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        EditText txtusername = findViewById(R.id.usernameS);
        EditText txtfullname = findViewById(R.id.fullnameS);
        EditText txtpassword = findViewById(R.id.passwordS);
        TextView dangnhap = findViewById(R.id.txtdangnhapS);

        Button dangky = findViewById(R.id.btndangkyS);

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtusername.getText().toString();
                String fullName = txtfullname.getText().toString();
                String passWord = txtpassword.getText().toString();
                if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord) && !TextUtils.isEmpty(fullName)){
                    mAuth.createUserWithEmailAndPassword(userName, passWord)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        String userId = mAuth.getCurrentUser().getUid();

                                        Map<String, Object> account = new HashMap<>();
                                        account.put("username", userName);
                                        account.put("fullname", fullName);
                                        account.put("password", passWord);

                                        db.collection("accounts").document(userId).set(account)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(SignUpActivity.this, "Luu du lieu thanh cong", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(SignUpActivity.this, "Loi khi du lieu duoc them vao firestore", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }else{
                                        Toast.makeText(SignUpActivity.this, "Dang ky that bai ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(SignUpActivity.this, "Vui long nhap day du thong tin dang ky", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

    }
}