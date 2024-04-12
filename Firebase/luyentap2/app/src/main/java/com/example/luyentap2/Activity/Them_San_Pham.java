package com.example.luyentap2.Activity;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.luyentap2.MainActivity;
import com.example.luyentap2.R;
import com.example.luyentap2.model.SanPham;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class Them_San_Pham extends AppCompatActivity {
    Context context;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    Uri uri;
    ActivityResultLauncher<String> stringActivityResultLauncher;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        context = Them_San_Pham.this;
        imageView = findViewById(R.id.imganh_Shoes_Them);
         firebaseFirestore = FirebaseFirestore.getInstance();
         auth = FirebaseAuth.getInstance();
         firebaseStorage = FirebaseStorage.getInstance();
         storageReference = firebaseStorage.getReference();
         Crud crud = new Crud(firebaseFirestore, context);
         stringActivityResultLauncher = registerForActivityResult(
                 new ActivityResultContracts.GetContent(),
                 new ActivityResultCallback<Uri>() {
                     @Override
                     public void onActivityResult(Uri result) {
                         if(result != null){
                             uri = result;
                             try{
                                 Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                 imageView.setImageBitmap(bitmap);
                             }catch (IOException e){
                                 Log.i(TAG, "loi" + e);
                             }
                         }
                     }
                 }
         );
        EditText txtname = findViewById(R.id.tensanpham_Shose_Them);
        EditText txtngayramat = findViewById(R.id.ngayramat_Shose_Them);
        EditText txtgia = findViewById(R.id.gia_Shose_Them);
        EditText txtsoluong = findViewById(R.id.soluong_Shose_Them);
//        EditText txttinhtrang= findViewById(R.id.tinhtrang_Shose_Them);

        Button them = findViewById(R.id.them_Shoes_Them);

         imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 stringActivityResultLauncher.launch("image/*");
             }
         });

         them.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String tensp = txtname.getText().toString();
                 String ngayramat = txtngayramat.getText().toString();
                 int gia = Integer.parseInt(txtgia.getText().toString());
                 int soluong = Integer.parseInt(txtsoluong.getText().toString());
//                 String tinhTrang = txttinhtrang.getText().toString();
            if(!TextUtils.isEmpty(tensp) && !TextUtils.isEmpty(ngayramat) && !TextUtils.isEmpty(txtgia.getText().toString()) && !TextUtils.isEmpty(txtsoluong.getText().toString())){
                //id nay dung de dinh danh duy nhat cho 1 doi tuong moi
                String id = UUID.randomUUID().toString();
                if(uri != null){
                    StorageReference imageRdf = storageReference.child("anhSanPham/" + UUID.randomUUID());
                    UploadTask uploadTask = imageRdf.putFile(uri);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> task = imageRdf.getDownloadUrl();
                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String hinh = uri.toString();
                                    SanPham sanPham = new SanPham(id, hinh,tensp, ngayramat,gia, soluong, "Con hang");
                                    crud.themSanPham(sanPham);
                                    startActivity(new Intent(context, MainActivity.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "Loi khong tai len duoc anh", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Khong add len duoc firestore", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }else{
                Toast.makeText(context, "Chua dien het thong tin", Toast.LENGTH_SHORT).show();
            }
             }
         });
    }
}