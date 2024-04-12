package com.example.luyentap.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.luyentap.R;
import com.example.luyentap.adapter.HomeAdapter;
import com.example.luyentap.adapter.Popular_item;
import com.example.luyentap.model.HomeCategory;
import com.example.luyentap.model.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {



    RecyclerView pop_rec, home_rec;
    //Popular
    ArrayList<PopularModel> poplist;
   Popular_item adapterP;

    //Home
    ArrayList<HomeCategory> homelist;
    HomeAdapter adapterH;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView pop_rec;
        ArrayList<PopularModel> poplist;
        Popular_item adapterP;

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            pop_rec = view.findViewById(R.id.pop_rec);
            pop_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

            poplist = new ArrayList<>();
            adapterP = new Popular_item(getContext(), poplist);
            pop_rec.setAdapter(adapterP);

            db.collection("PupolarProducts")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                    PopularModel popularModel = documentSnapshot.toObject(PopularModel.class);
                                    poplist.add(popularModel);
                                }
                                adapterP.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            TextView addPopular = view.findViewById(R.id.pop_add);


            return view;
        }
    }
