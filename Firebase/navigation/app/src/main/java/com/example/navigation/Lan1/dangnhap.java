package com.example.navigation.Lan1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class dangnhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap2);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        EditText txtuser = findViewById(R.id.txtuser_N);
        EditText txtpass = findViewById(R.id.txtpass_N);

        Button dangnhap = findViewById(R.id.btnlogin_N);
        TextView dangky = findViewById(R.id.dangky);

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtuser.getText().toString();
                String pass = txtpass.getText().toString();
                if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)){
                    auth.signInWithEmailAndPassword(user, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        String userId = auth.getCurrentUser().getUid();
                                        Toast.makeText(dangnhap.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                                        firebaseFirestore.collection("Acounts").document(userId)
                                                .get()
                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        if(documentSnapshot.exists()){
                                                            String user = documentSnapshot.getString("user");
                                                         }
                                                        startActivity(new Intent(com.example.navigation.Lan1.dangnhap.this, trangchu1.class));
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(dangnhap.this, "Dang ky that bai", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                }else {
                                        Toast.makeText(dangnhap.this, "Dang nhap taht bai", Toast.LENGTH_SHORT).show();
                                    }
                                    }
                            });
                }else{
                    Toast.makeText(dangnhap.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}