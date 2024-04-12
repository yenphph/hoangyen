package com.example.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Authentication;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class dangnhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        EditText txtusername = findViewById(R.id.txtuser_N);
        EditText txtpassword = findViewById(R.id.txtpass_N);
        Button dangnhap = findViewById(R.id.btnlogin_N);



        dangnhap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = txtusername.getText().toString();
                String password = txtpassword.getText().toString();
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(dangnhap.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Đăng nhập thành công
                                        String userId = mAuth.getCurrentUser().getUid();
                                        // Nếu đăng nhập thành công, chúng ta lấy userId của người dùng đã đăng nhập và
                                        // sử dụng nó để truy vấn thông tin người dùng từ Firestore.
                                        // Truy vấn thông tin người dùng từ Firestore
                                        Toast.makeText(dangnhap.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();

                                        db.collection("users").document(userId)
                                                .get()
                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        if (documentSnapshot.exists()) {
                                                            // Dữ liệu của người dùng tồn tại trong Firestore
                                                        String user = documentSnapshot.getString("email");

//                                                        int age = documentSnapshot.getLong("age").intValue();
                                                            Toast.makeText(dangnhap.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                                                            // Bạn có thể sử dụng thông tin này cho mục đích hiển thị hoặc xử lý khác
                                                        }
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        // Lỗi khi truy vấn dữ liệu người dùng từ Firestore
                                                        Toast.makeText(dangnhap.this, "Loi truy van du lieu nguoi dung tu firestore", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    } else {
                                        // Đăng nhập thất bại, xử lý lỗi ở đây
                                        Toast.makeText(dangnhap.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(dangnhap.this, "Chua dien het du lieu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}