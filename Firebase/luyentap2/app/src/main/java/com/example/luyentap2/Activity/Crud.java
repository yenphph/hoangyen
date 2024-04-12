package com.example.luyentap2.Activity;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.luyentap2.model.SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class Crud {
    private final FirebaseFirestore firebaseFirestore;
    private final Context context;

    public Crud(FirebaseFirestore firebaseFirestore, Context context) {
        this.firebaseFirestore = firebaseFirestore;
        this.context = context;
    }
    public  void themSanPham(SanPham sanPham){
        HashMap<String, Object> hmSanPham = sanPham.hmSanPham();
        firebaseFirestore.collection("SanPham").document(sanPham.getId())
                .set(hmSanPham).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
