package com.example.options1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ArrayList<DataClass> list;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    //tim kiem
    SearchView search;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);

        recyclerView = findViewById(R.id.recycleview);
        //tim kiem
        search = findViewById(R.id.search);
        search.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setCancelable(false);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();

        list = new ArrayList<>();
        adapter = new MyAdapter(MainActivity.this, list);
        recyclerView.setAdapter(adapter);


        databaseReference = FirebaseDatabase.getInstance().getReference("Android");
        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    DataClass dataClass = item.getValue(DataClass.class);
                    //lien quan den xoa key khong cos constructor
                    dataClass.setKey(item.getKey());

                    list.add(dataClass);
                }
                adapter.notifyDataSetChanged();
//                alertDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                alertDialog.dismiss();
            }
        });

//        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //pending
////                searchList(newText);
//                return true;
//            }
//        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Upload.class));
            }
        });
    }
//    public  void searchList(String text){
//        ArrayList<DataClass> searchList = new ArrayList<>();
//        for(DataClass dataClass : list){
//
//            if(dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase())){
//                searchList.add(dataClass);
//            }
//        }
//        adapter.searchDataList(searchList);
//    }
}