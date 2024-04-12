package com.example.navigation.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.R;
import com.example.navigation.adapter.Homeadapter;
import com.example.navigation.adapter.NavCategoryadapter;
import com.example.navigation.modal.HomeCategory;
import com.example.navigation.modal.NavCategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Category_Frag extends Fragment {
    FirebaseFirestore db;
    ArrayList<NavCategoryModel> listN;
    RecyclerView recyclerView;
    NavCategoryadapter navCategoryadapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_frag, container, false);
        recyclerView = view.findViewById(R.id.cat_rec);
        //khoi tao de thao tac voi firebase
        db = FirebaseFirestore.getInstance();

        //Category
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        listN = new ArrayList<>();
        navCategoryadapter = new NavCategoryadapter(getActivity(), listN);
        recyclerView.setAdapter(navCategoryadapter);

        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NavCategoryModel navCategoryModel = document.toObject(NavCategoryModel.class);
                                listN.add(navCategoryModel);
                                navCategoryadapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return view;
    }
}
