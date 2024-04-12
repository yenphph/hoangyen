package com.example.navigation.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.navigation.R;
import com.example.navigation.adapter.NavCategoryDetailedAdapter;
import com.example.navigation.adapter.NavCategoryadapter;
import com.example.navigation.modal.NavCategoryDetailedModel;
import com.example.navigation.modal.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NavCategory extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<NavCategoryDetailedModel> listC;
NavCategoryDetailedAdapter navCategoryDetailedAdapter;
FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);
        recyclerView = findViewById(R.id.nav_cat_det_rec);
        String type = getIntent().getStringExtra("type");
        listC = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        navCategoryDetailedAdapter = new NavCategoryDetailedAdapter(this, listC);
        recyclerView.setAdapter(navCategoryDetailedAdapter);
firebaseFirestore = FirebaseFirestore.getInstance();

//              TAIj sao phải cmt cai này?????????HẢ



//        firebaseFirestore.collection("NavCategoryDetailed")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful()){
//                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
//                                NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
//                                listC.add(navCategoryDetailedModel);
//                                navCategoryDetailedAdapter.notifyDataSetChanged();
//                            }
//                        }else{
//                            Toast.makeText(NavCategory.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
        //get beakfast
        if(type != null && type.equalsIgnoreCase("breakfast")){
            firebaseFirestore.collection("NavCategoryDetailed").whereEqualTo("type", "breakfast").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModell = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        listC.add(navCategoryDetailedModell);
                        navCategoryDetailedAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

    }
}