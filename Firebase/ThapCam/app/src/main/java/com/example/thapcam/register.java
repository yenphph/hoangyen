package com.example.thapcam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
private EditText email, pass;
private Button register;
private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        register = findViewById(R.id.register);
        auth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_pass = pass.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pass)){
                    Toast.makeText(register.this, "Empty thanh cong", Toast.LENGTH_SHORT).show();
                }else if(txt_pass.length() < 6){
                    Toast.makeText(register.this, "Empty credentials", Toast.LENGTH_SHORT).show();
                }else{
                    register(txt_email, txt_pass);
                }

            }

            private void register(String email, String pass) {
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(register.this, "thanh cong", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(register.this, "that bai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}