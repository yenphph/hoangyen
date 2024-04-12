package com.example.buoi1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
     DatabaseReference databaseReference;
     RecyclerView rcv;
     ArrayList<user> list;

     Button btnadd;
     useradapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        rcv = findViewById(R.id.rcv);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addne addne = new addne();
                addne.them(MainActivity.this);
            }
        });
        readData();
    }
    private void readData(){
        databaseReference.child("USER").orderByChild("userName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    user user = dataSnapshot.getValue(com.example.buoi1.user.class);
                    list.add(user);
                }
                adapter = new useradapter(MainActivity.this, list);
                rcv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public class addne{
        public  void them(Context context){
            final Dialog dialog = new Dialog(context);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.add_user);

            EditText txtten = dialog.findViewById(R.id.txtaddten);
            EditText txtemail = dialog.findViewById(R.id.txtaddemail);
            EditText txtdat = dialog.findViewById(R.id.txtaddnuoc);

            Button btnaddd = dialog.findViewById(R.id.btnaddthem);
            Button btnxoa = dialog.findViewById(R.id.btnaddhuy);

            btnxoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            btnaddd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = "user" + new Date().getTime();
                    String name = txtten.getText().toString();
                    String email = txtemail.getText().toString();
                    String datnuoc = txtdat.getText().toString();

                    if(name.isEmpty() || email.isEmpty() || datnuoc.isEmpty()){
                        Toast.makeText(context, "Vui long nhap lai...", Toast.LENGTH_SHORT).show();
                    }else{
                        databaseReference.child("USER").child(id).setValue(new user(id, name, email, datnuoc));
                        Toast.makeText(context, "DONE", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            });
        }
    }
}