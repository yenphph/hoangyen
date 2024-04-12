package com.example.navigation.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.navigation.R;
import com.example.navigation.modal.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {
    TextView quantity;
    int totalQuantity =1;
    int totalPrice = 0;
ImageView detailed_img;
TextView price, rating, description;
Button addToCart;
ImageView addItem, removeItem;

ViewAllModel viewAllModel = null;
FirebaseFirestore firebaseFirestore;
FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        detailed_img = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);


        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        //lien quan den doc du lieu
        final  Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
        }
        //gan chung
        quantity = findViewById(R.id.quantity);


        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        description = findViewById(R.id.deatailed_description);

        //doc du lieu
        if(viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailed_img);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("price" + viewAllModel.getPrice() + "/kg");

            totalPrice = viewAllModel.getPrice() * totalQuantity;


            if (viewAllModel.getType().equals("eggs")){
                price.setText(Integer.toString(viewAllModel.getPrice()) + "/dozen");
            }
        }
        addToCart = findViewById(R.id.addtoCart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pending
                addToCart();
            }
        });
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;

                }
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                }
            }
        });
    }
    private void addToCart(){
        String saveCurrenDate, saveCurrentTime;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrenDate = simpleDateDate.format(calendar.getTime());

        SimpleDateFormat simpleDateTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = simpleDateTime.format(calendar.getTime());
        //Tao collection
        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("productName", viewAllModel.getName());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("saveCurrenDate", saveCurrenDate);
        cartMap.put("saveCurrentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);
        //Tao collection

        firebaseFirestore.collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this, "Added to a cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}