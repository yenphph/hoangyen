
package com.example.navigation.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.navigation.R;
import com.example.navigation.modal.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        firebaseFirestore = FirebaseFirestore.getInstance();

        ArrayList<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemLists");

        if(list != null && list.size() > 0){
            for (MyCartModel myCartModel : list){
                //Tao collection
                final HashMap<String, Object> cartMap = new HashMap<>();
                cartMap.put("productName", myCartModel.getProductName());
                cartMap.put("productPrice", myCartModel.getProductPrice());
                cartMap.put("saveCurrenDate", myCartModel.getSaveCurrenDate());
                cartMap.put("saveCurrentTime", myCartModel.getSaveCurrentTime());
                cartMap.put("totalQuantity", myCartModel.getTotalQuantity());
                cartMap.put("totalPrice", myCartModel.getTotalPrice());
                //Tao collection

                firebaseFirestore.collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(PlacedOrderActivity.this, "Added to a cart", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
    }
}