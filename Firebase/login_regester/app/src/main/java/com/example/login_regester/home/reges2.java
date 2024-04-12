package com.example.login_regester.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_regester.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class reges2 extends AppCompatActivity {
    Button signUp;
    EditText name, email, pass;
    TextView login;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reges2);
        signUp = findViewById(R.id.reg_sign_up);
        email = findViewById(R.id.reg_email);
        name = findViewById(R.id.reg_name);
        pass = findViewById(R.id.reg_pass);
        login = findViewById(R.id.reg_login);
        progressBar = findViewById(R.id.progresbar);
        progressBar.setVisibility(View.GONE);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(reges2.this, login.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void createUser() {
        final String username = name.getText().toString().trim();
        final String userEmail = email.getText().toString().trim();
        String userPassword = pass.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPassword.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create User
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //cho cmt là cái phần đầu tiên chỗ thư mục mà mình thay đổi đó á 173 -177
//                            String userId = auth.getCurrentUser().getUid();
//                            DatabaseReference currentUserDb = databaseReference.child(userId);
//                            currentUserDb.child("username").setValue(username);
//                            currentUserDb.child("email").setValue(userEmail);
                            // User registered successfully, now add user details to the database
                            String userId = auth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = databaseReference.child(userId).child("username");
                            currentUserDb.child("username").setValue(username);
                            currentUserDb.child("email").setValue(userEmail);
                            currentUserDb.child("pass").setValue(userPassword);
                            Toast.makeText(reges.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(reges.this, login.class));
                            finish();
                        } else {
                            progressBar.setVisibility(View.GONE);

                            // If registration fails, display a message to the user.
                            Toast.makeText(reges.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}