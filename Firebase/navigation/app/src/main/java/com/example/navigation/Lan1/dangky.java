package com.example.navigation.Lan1;

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
        setContentView(R.layout.activity_dangky2);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();

        EditText txtuser = findViewById(R.id.txtuser_K);
        EditText txtfull = findViewById(R.id.txthoten_K);
        EditText txtpass = findViewById(R.id.txtpass_K);

        Button dangky = findViewById(R.id.btnsingup_K);
        TextView dangnhap = findViewById(R.id.dangnhap);

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtuser.getText().toString();
                String full = txtfull.getText().toString();
                String pass = txtpass.getText().toString();

               if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(full)){
                   mAuth.createUserWithEmailAndPassword(user, pass)
                           .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if(task.isSuccessful()){
                                       String userId = mAuth.getCurrentUser().getUid();
                                       Map<String, Object> acount = new HashMap<>();
                                       acount.put("user", user);
                                       acount.put("full", full);
                                       Toast.makeText(dangky.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();

                                       firebaseFirestore.collection("Acounts").document(userId).set("acount")
                                               .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                   @Override
                                                   public void onSuccess(Void unused) {
                                                       Toast.makeText(dangky.this, "Luu du lieu thanh cong", Toast.LENGTH_SHORT).show();
                                                   }
                                               }).addOnFailureListener(new OnFailureListener() {
                                                   @Override
                                                   public void onFailure(@NonNull Exception e) {
                                                       Toast.makeText(dangky.this, "Luu du lieu that bai", Toast.LENGTH_SHORT).show();
                                                   }
                                               }).addOnFailureListener(new OnFailureListener() {
                                                   @Override
                                                   public void onFailure(@NonNull Exception e) {
                                                       Toast.makeText(dangky.this, "Loi khi du lieu vao firestore", Toast.LENGTH_SHORT).show();
                                                   }
                                               });
                                   }
                               }
                           });
               }else{
                   Toast.makeText(dangky.this, "Vui long nhap day du thong tin dang ky", Toast.LENGTH_SHORT).show();
               }
            }
        });
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.navigation.Lan1.dangky.this, dangnhap.class));
            }
        });
    }
}