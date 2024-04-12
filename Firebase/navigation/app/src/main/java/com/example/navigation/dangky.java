package com.example.navigation;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        // Trong RegisterActivity hoặc RegisterFragment
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText txtuser = findViewById(R.id.txtuser_K);
        EditText txthoten = findViewById(R.id.txthoten_K);
        EditText txtpass = findViewById(R.id.txtpass_K);
        TextView dangnhap = findViewById(R.id.dangnhap);
        Button dangky = findViewById(R.id.btnsingup_K);

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtuser.getText().toString();
                String fullName = txthoten.getText().toString();
                String password = txtpass.getText().toString();
// tạo một tài khoản người dùng mới sử dụng địa chỉ email và mật khẩu mà người dùng nhập
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(fullName)) {
                    // email, password và fullName không rỗng hoặc null, có thể gọi hàm createUserWithEmailAndPassword
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(dangky.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Đăng ký thành công
                                        String userId = mAuth.getCurrentUser().getUid();
                                        // Lưu thông tin người dùng vào Firestore
//                                // Khi đăng ký thành công, getCurrentUser() trả về một đối tượng FirebaseUser,
//                                // đại diện cho người dùng vừa được tạo mới. getUid() trả về ID định danh duy nhất của
//                                // người dùng này (một chuỗi dài) từ dịch vụ Firebase Authentication. Bạn có thể sử dụng ID
//                                // này để liên kết thông tin người dùng với dữ liệu trong Firestore hoặc thực hiện các tác vụ
//                                // khác liên quan đến người dùng.

                                        // Lưu thông tin người dùng vào Firestore
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("email", email);
                                        user.put("fullName", fullName);
                                            //them user moi vao document voi id do
                                        db.collection("users").document(userId).set(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        // Lưu dữ liệu thành công
                                                        Toast.makeText(dangky.this, "Lưu dữ liệu thành công", Toast.LENGTH_SHORT).show();
                                                        // Chuyển hướng người dùng đến màn hình chính hoặc màn hình đăng nhập
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        // Lỗi khi lưu dữ liệu vào Firestore
                                                        Toast.makeText(dangky.this, "Lỗi khi lưu dữ liệu vào Firestore", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    } else {
                                        // Đăng ký thất bại, xử lý lỗi ở đây
                                        Toast.makeText(dangky.this, "Đăng ký thất bại. Vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // Hiển thị thông báo lỗi khi dữ liệu không hợp lệ
                    Toast.makeText(dangky.this, "Vui lòng nhập đầy đủ thông tin đăng ký.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dangky.this, com.example.navigation.dangnhap.class));
            }
        });
    }
}