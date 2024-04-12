//package com.example.options1;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.github.clans.fab.FloatingActionButton;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//
//import java.util.ArrayList;
//import java.util.prefs.Preferences;
//
//public class detail extends AppCompatActivity {
//    TextView detailDesc, detailTitle, detailLang;
//    ImageView detailImage;
//    //lien quan den xoa
//    FloatingActionButton delete, edit;
//    String key="";
//    String imgURI ="";
//    MyAdapter adapter;
//    ArrayList<detail> list;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//        detailDesc = findViewById(R.id.detailDesc);
//        detailImage = findViewById(R.id.detailImage);
//        detailTitle = findViewById(R.id.detailTitle);
//        //  lien quan den update
//        detailLang = findViewById(R.id.detailLang);
//        //  lien quan den xoa
//        delete = findViewById(R.id.delete);
//        //  lien quan den  update
//        edit = findViewById(R.id.update);
//
//        Bundle bundle = getIntent().getExtras();
//        if(bundle != null){
////           detailTitle.setText(bundle.getString("title"));
////           detailDesc.setText(bundle.getString("description"));
////            //  lien quan den  update
////           detailLang.setText(bundle.getString("Language"));
////            //lien quan den xoa
////            key = bundle.getString("key");
////            imgURI= bundle.getString("image");
////
////            Glide.with(this).load(bundle.get("image")).into(detailImage);
//            String key = bundle.getString("key");
//            FirebaseDatabase.getInstance().getReference("Android")
//                    .child(key).addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            DataClass dataClass = snapshot.getValue(DataClass.class);
//                            detailTitle.setText(dataClass.getDataTitle());
//                            detailDesc.setText(dataClass.getDataDesc());
//                            detailLang.setText(dataClass.getDataLang());
//                            Glide.with(detail.this).load(dataClass.getDataImage()).into(detailImage);
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//        }
//        //lien quan den xoa
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Android");
//                FirebaseStorage storage = FirebaseStorage.getInstance();
//
//                StorageReference storageReference = storage.getReferenceFromUrl(imgURI);
//                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        reference.child(key).removeValue();
//                        Toast.makeText(detail.this, "Deleteed", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        finish();
//                    }
//                }) ;
//            }
//        });
//        //lien quan den update
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                list = new ArrayList<>();
////                Intent intent = new Intent(detail.this, updateActivity.class)
////                        .putExtra("title", detailTitle.getText().toString())
////                        .putExtra("description", detailDesc.getText().toString())
////                        .putExtra("Language", detailLang.getText().toString())
////                        .putExtra("image", imgURI)
////                        .putExtra("key", key);
////                startActivity(intent);
//
//                Intent intent = new Intent(detail.this, updateActivity.class);
//                //do dể tránh bị đóng ứng dụng phần detai nên cần putExtras(LƯU Ý)
//                intent.putExtras(bundle);
////                intent.putExtra("key", key);
//                startActivity(intent);
//                finish();
//
//            }
//        });
//
//    }
//}
package com.example.options1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.prefs.Preferences;

    public class detail extends AppCompatActivity {
        TextView detailDesc, detailTitle, detailLang;
        ImageView detailImage;
        //lien quan den xoa
        FloatingActionButton delete, edit;
        String key = "";
        String imgURI = "";
        MyAdapter adapter;
        ArrayList<com.example.options1.detail> list;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);
            detailDesc = findViewById(R.id.detailDesc);
            detailImage = findViewById(R.id.detailImage);
            detailTitle = findViewById(R.id.detailTitle);
            //  lien quan den update
            detailLang = findViewById(R.id.detailLang);
            //  lien quan den xoa
            delete = findViewById(R.id.delete);
            //  lien quan den  update
            edit = findViewById(R.id.update);

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
           detailTitle.setText(bundle.getString("title"));
           detailDesc.setText(bundle.getString("description"));
            //  lien quan den  update
           detailLang.setText(bundle.getString("Language"));
            //lien quan den xoa
            key = bundle.getString("key");
            imgURI= bundle.getString("image");

            Glide.with(this).load(bundle.get("image")).into(detailImage);

                String key = bundle.getString("key");
                FirebaseDatabase.getInstance().getReference("Android")
                        .child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    DataClass dataClass = snapshot.getValue(DataClass.class);
                                    if (dataClass != null) {
                                        detailTitle.setText(dataClass.getDataTitle());
                                        detailDesc.setText(dataClass.getDataDesc());
                                        detailLang.setText(dataClass.getDataLang());
                                        Glide.with(detail.this).load(dataClass.getDataImage()).into(detailImage);
                                    } else {
                                        // Xử lý khi dữ liệu null
                                        Toast.makeText(detail.this, "khong ton tai", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // Xử lý khi snapshot không tồn tại
                                    Toast.makeText(detail.this, "khong ton tai", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                // Xử lý khi có lỗi kết nối đến Firebase
                            }
                        });

            }
            //lien quan den xoa
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imgURI != null && !imgURI.isEmpty()) {
                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Android");
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageReference = storage.getReferenceFromUrl(imgURI);
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                reference.child(key).removeValue();
                                Toast.makeText(detail.this, "Deleted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Xử lý khi xóa không thành công
                                Toast.makeText(detail.this, "Delete failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        // Xử lý khi imgURI là rỗng hoặc null
                        Toast.makeText(detail.this, "Invalid image URI", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //lien quan den update
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                list = new ArrayList<>();
//                Intent intent = new Intent(detail.this, updateActivity.class)
//                        .putExtra("title", detailTitle.getText().toString())
//                        .putExtra("description", detailDesc.getText().toString())
//                        .putExtra("Language", detailLang.getText().toString())
//                        .putExtra("image", imgURI)
//                        .putExtra("key", key);
//                startActivity(intent);

                    Intent intent = new Intent(com.example.options1.detail.this, updateActivity.class);
                    //do dể tránh bị đóng ứng dụng phần detai nên cần putExtras(LƯU Ý)
                    intent.putExtras(bundle);
//                intent.putExtra("key", key);
                    startActivity(intent);
                    finish();

                }
            });
        }
    }
