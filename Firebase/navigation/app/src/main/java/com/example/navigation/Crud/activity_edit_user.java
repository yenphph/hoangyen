package com.example.navigation.Crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navigation.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class activity_edit_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        EditText firstName = findViewById(R.id.fisrtnameU);
        EditText lastName = findViewById(R.id.lastnameU);
        EditText phone = findViewById(R.id.phoneU);
        EditText bio = findViewById(R.id.bioU);

        firstName.setText(App.ao.getFirstName());
        lastName.setText(App.ao.getLastName());
        phone.setText(App.ao.getPhone());
        bio.setText(App.ao.getBio());

        Button btnsave = findViewById(R.id.btnSave);
        Button btndelete = findViewById(R.id.btndelete);
btndelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        db.collection("Aos").document(App.ao.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity_edit_user.this, "Uers delete", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity_edit_user.this, "Failed to do", Toast.LENGTH_SHORT).show();

            }
        }) ;
    }
});
btnsave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Map<String, Object> ao = new HashMap<>();
        ao.put("firstName", Objects.requireNonNull(firstName.getText().toString()));
        ao.put("lastName", Objects.requireNonNull(lastName.getText().toString()));
        ao.put("phone", Objects.requireNonNull(phone.getText().toString()));
        ao.put("bio", Objects.requireNonNull(bio.getText().toString()));
        db.collection("Aos").document(App.ao.getId()).set(ao).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity_edit_user.this, "Saved thanh cong", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity_edit_user.this, " Save That bai", Toast.LENGTH_SHORT).show();
            }
        });
    }
});

    }
}