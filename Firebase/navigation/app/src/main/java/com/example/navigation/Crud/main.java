package com.example.navigation.Crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.navigation.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class main extends AppCompatActivity {
    private UserrAdapter adapter;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton addUserButton = findViewById(R.id.allUers);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main.this, AddUserActivity.class));
            }
        });

        loadUserData(recyclerView);

        FloatingActionButton refreshButton = findViewById(R.id.refres);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUserData(recyclerView);
            }
        });
    }

    private void loadUserData(final RecyclerView recyclerView) {
        firebaseFirestore.collection("Aos").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<Ao> arrayList = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            Ao ao = doc.toObject(Ao.class);
                            ao.setId(doc.getId());
                            arrayList.add(ao);
                        }
                        adapter = new UserrAdapter( main.this, arrayList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new UserrAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(Ao ao) {
                                App.ao = ao;
                                Intent intent = new Intent(main.this, activity_edit_user.class);
                                startActivity(intent);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(main.this, "Failed to get data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
