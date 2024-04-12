package com.example.navigation.Crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navigation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        EditText firstName = findViewById(R.id.fisrtname);
        EditText lastName = findViewById(R.id.lastname);
        EditText phone = findViewById(R.id.phone);
        EditText bio = findViewById(R.id.bio);

        Button btnadd = findViewById(R.id.btnAdd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> ao = new HashMap<>();
                ao.put("firstName", Objects.requireNonNull(firstName.getText().toString()));
                ao.put("lastName", Objects.requireNonNull(lastName.getText().toString()));
                ao.put("phone", Objects.requireNonNull(phone.getText().toString()));
                ao.put("bio", Objects.requireNonNull(bio.getText().toString()));

                db.collection("Aos").add(ao).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddUserActivity.this, "User added thanh cong", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(AddUserActivity.this, "Failed to add user", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}