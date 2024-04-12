package com.example.login_regester;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    TextInputEditText edtemail, edtpass;
    Button btnres;
    TextView txtloginlai;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        edtemail = findViewById(R.id.email);
        edtpass = findViewById(R.id.pass);
        btnres = findViewById(R.id.btn_register);
        txtloginlai = findViewById(R.id.loginNow);

        txtloginlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });

        btnres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtemail.getText().toString();
                String pass = edtpass.getText().toString();

                // Kiểm tra email và mật khẩu không trống
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(register.this, "Nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Nếu email và mật khẩu không trống, tiến hành đăng ký
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Xử lý khi xác thực thành công
                                    Toast.makeText(register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                    // Chuyển hướng đến hoạt động tiếp theo
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Xử lý khi xác thực thất bại
                                    Toast.makeText(register.this, "Xác thực thất bại.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}