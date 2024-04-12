package com.example.luyentap2;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luyentap2.Activity.Them_San_Pham;
import com.example.luyentap2.adapter.SanPhamAdapter;
import com.example.luyentap2.model.SanPham;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Shoes_Frag extends Fragment {

    ArrayList<SanPham> list;
    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    SanPhamAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shoes_frag, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = view.findViewById(R.id.rcsanpham_Home);
       list = new ArrayList<>();
       GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
       recyclerView.setLayoutManager(gridLayoutManager);

       adapter = new SanPhamAdapter(getContext(), list);
       recyclerView.setAdapter(adapter);

       //Đưa lên list
            listSanPhamChange();
        //
        TextView add = view.findViewById(R.id.them);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Them_San_Pham.class));
            }
        });
        return view;
    }
    private  void listSanPhamChange(){
        firebaseFirestore.collection("SanPham")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error !=null){
                            Log.i(TAG, "loi" + error);
                            return;
                        }
                        if(value != null){
                          for (DocumentChange documentChange : value.getDocumentChanges()){
                              switch (documentChange.getType()){
                                  case ADDED:
                                      list.clear();
                                      for (DocumentSnapshot documentSnapshot : value.getDocuments()){
                                          SanPham sanPham = documentSnapshot.toObject(SanPham.class);
                                          list.add(sanPham);
                                      }
                                      adapter.notifyDataSetChanged();
                                      break;
                                  case MODIFIED:
                                      adapter.notifyDataSetChanged();
                                      break;
                                  case REMOVED:
                                      documentChange.getDocument().toObject(SanPham.class);
                                      list.remove(documentChange.getOldIndex());
                                      adapter.notifyDataSetChanged();
                                      break;
                              }
                          }
                        }
                    }
                });
    }
}
