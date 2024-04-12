package com.example.options1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class updateActivity extends AppCompatActivity {
    ImageView updateImage;
    Button updateButton;
    EditText updateDesc, updateTitle, updateLang;
    String title, desc, lang;
    String imageUri;
    String key, oldImageURI;
    Uri uri;
    MyAdapter adapter;
    DatabaseReference databaseReference;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        updateButton = findViewById(R.id.updateButton);
        updateDesc = findViewById(R.id.upDateDesc);
        updateImage = findViewById(R.id.updateimg);
        updateLang = findViewById(R.id.upDateLang);
        updateTitle = findViewById(R.id.upDateTitle);

        //viet them update
        Bundle bundle1 = getIntent().getExtras();
        String key = bundle1.getString("key");
        updateData(key, bundle1);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            updateImage.setImageURI(uri);
                        }else{
                            Toast.makeText(updateActivity.this, "NO ImageView selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Glide.with(updateActivity.this).load(bundle.getString("image")).into(updateImage);
            updateTitle.setText(bundle.getString("title"));
            updateLang.setText(bundle.getString("Language"));
            updateDesc.setText(bundle.getString("description"));
            key = bundle.getString("key");
            oldImageURI = bundle.getString("image");
        }
        //thieu key con chua vao duoc firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Android").child(key);
        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                //pending
                Intent intent = new Intent(updateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public  void saveData(){
        storageReference = FirebaseStorage.getInstance().getReference().child("Android").child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(updateActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

//        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        imageUri = uri.toString();
//                        update(); // Gọi update() sau khi nhận được URL tải xuống
//
//                        dialog.dismiss();
//                    }
//                });
//            }
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uri1 = uriTask.getResult();
                imageUri = uri1.toString();
                dialog.dismiss();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }
//    public  void updateData(){
//            title = updateTitle.getText().toString().trim();
//            desc = updateDesc.getText().toString().trim();
//            lang = updateLang.getText().toString().trim();
//
//            DataClass dataClass = new DataClass(title, desc, lang, imageUri);
//
//
//
//        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful()){
//                        // update anh 151-157 la anh
////                        StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURI);
////                        reference.delete();
//
////                         reference.child("Android").child(key);
//                        adapter.notifyDataSetChanged();
////                        reference.notifyAll();
//                        Toast.makeText(updateActivity.this, "update", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(updateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//    }
//do dể tránh bị đóng ứng dụng phần detai nên cần truyền tham số bundle(LƯU Ý)
public  void updateData(String key, Bundle bundle){

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Android").child(key);
    reference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                DataClass dataClass = snapshot.getValue(DataClass.class);
                if (dataClass != null) {
                    updateTitle.setText(dataClass.getDataTitle());
                    updateLang.setText(dataClass.getDataLang());
                    updateDesc.setText(dataClass.getDataDesc());

                    updateButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String newLang = updateLang.getText().toString().trim();
                            String newDesc = updateDesc.getText().toString().trim();
                            if (newLang.isEmpty() || newDesc.isEmpty()) {
                                Toast.makeText(updateActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            dataClass.setDataLang(newLang);
                            dataClass.setDataDesc(newDesc);

                            Map<String, Object> updateData = new HashMap<>();
                            updateData.put("dataLang", newLang);
                            updateData.put("dataDesc", newDesc);

                            reference.updateChildren(updateData)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(updateActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(updateActivity.this, detail.class);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(updateActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(updateActivity.this, detail.class);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                        }
                    });
                } else {
                    // Xử lý trường hợp dataClass là null
                }
            } else {
                // Xử lý trường hợp không có dữ liệu trong snapshot
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            // Xử lý lỗi khi đọc dữ liệu từ Firebase
        }
    });
//    FirebaseDatabase.getInstance().getReference("Android").child(key)
//            .addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    DataClass dataClass = snapshot.getValue(DataClass.class);
//                    //snapshot bản ghi theo key lay tu
//                    updateTitle.setText(dataClass.getDataTitle());
//                    updateLang .setText(dataClass.getDataLang());
//                    updateDesc.setText(dataClass.getDataDesc());
//
//                    updateButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if(updateLang.getText().toString().isEmpty()){
//                                Toast.makeText(updateActivity.this, "Để trông", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                            if(updateDesc.getText().toString().isEmpty()){
//                                Toast.makeText(updateActivity.this, "Để trông", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                            String lang = updateLang.getText().toString();
//                            String desc = updateDesc.getText().toString();
//                            dataClass.setDataLang(lang);
//                            dataClass.setDataDesc(desc);
//                           DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Android").child(dataClass.getDataTitle());
//                           Map<String, Object> map = new HashMap<>();
//                           map.put("dataDesc", dataClass.getDataDesc());
//                           map.put("dataLang", dataClass.getDataLang());
//                           reference.updateChildren(map, new DatabaseReference.CompletionListener() {
//                               @Override
//                               public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                                   if(error == null){
//
//                                       Toast.makeText(updateActivity.this, "thanh cong", Toast.LENGTH_SHORT).show();
//                                       Intent intent = new Intent(updateActivity.this, detail.class);
//                                       intent.putExtras(bundle);
//                                       startActivity(intent);
//                                       finish();
//                                   }else{
//                                       Toast.makeText(updateActivity.this, "that bai", Toast.LENGTH_SHORT).show();
//                                       Intent intent = new Intent(updateActivity.this, detail.class);
//                                       intent.putExtras(bundle);
//                                       startActivity(intent);
//                                       finish();
//                                   }
//                               }
//                           });
//                        }
//                    });
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
}
}
