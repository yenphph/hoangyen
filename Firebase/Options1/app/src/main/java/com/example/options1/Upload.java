//package com.example.options1;
//
//import androidx.activity.result.ActivityResult;
//import androidx.activity.result.ActivityResultCallback;
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.google.android.gms.auth.api.signin.internal.Storage;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.tabs.TabLayout;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//import java.text.DateFormat;
//import java.util.Calendar;
//
//public class Upload extends AppCompatActivity {
//    ImageView uploadimg;//noi hien thi hanh anh ma nguoi dung hien thi hinh anh tu thiet bo cua ho
//    Button saveButton;
//    EditText uploadTopic, uploadDesc, uploadLang;
//    String imageURL;
//    Uri uri;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_upload);
//        uploadimg = findViewById(R.id.uploadimg);
//        uploadDesc = findViewById(R.id.updateDesc);
//        uploadTopic = findViewById(R.id.updateTopic);
//        uploadLang = findViewById(R.id.updateLang);
//        saveButton = findViewById(R.id.savebutton);
//
//
////nhan ket qua tu hoat dong mo ra de chon hinh anh
//
//        //goi thu vien(tach anh ra ra khoi )
//
//        //cmt 55 -71 vi no de cap nhat duoc thi phai cap nhat anh
//        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),//no dinh nghia kieu du lieu ma ban mong doi tu hoat dong ma bạn mo ra
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if(result.getResultCode() == Activity.RESULT_OK){
//                            //nhan du lieu ket qua tra ve. Trong truong hop nay day la duog dan uri cua hinh anh da được chọn
//                            Intent data = result.getData();
//                            //luu duong dan bien uri vao uri
//                            uri = data.getData();
//                            //hien thi hinh anh có trên img
//                            uploadimg.setImageURI(uri);
//                        }else{
//                            Toast.makeText(Upload.this, "No img selected", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );
//        uploadimg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent photoIntent = new Intent(Intent.ACTION_PICK);//được tạo đẻ chọn hình ảnh
//                photoIntent.setType("image/*");
//                activityResultLauncher.launch(photoIntent);
//            }
//        });
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveData();
//                //pending
//            }
//        });
//
//    }
//    public  void saveData(){
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference()
//                .child("Android").child(uri.getLastPathSegment());
//        AlertDialog.Builder builder = new AlertDialog.Builder(Upload.this);
//        //dieu nay dat cho nguoi dung khong the huy hoi thoai bang cach nhan nut black hoac nhan ra ngoai vung hoi thoai
//        builder.setCancelable(false);
//        builder.setView(R.layout.progress_layout);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//        // putFile(uri qua trinh tai hinh anh len uri
//        //add.... đây la qua trinh lang nghe sự kiện khi tải lên thành công
//        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            //khi tệp tin đã được tải lên thành công onSucces se được gọi với một đối tượng taskSnapshot chứa thong tin về tệp tin đã tải lên
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                // đây là cách lấy đường dã đã tải lên thành công
//                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
//                while (!uriTask.isComplete());
//                Uri uri1 = uriTask.getResult();//lấy đường dẫ đã tải xuống từ uri
//                imageURL = uri1.toString();//luu đường dẫn vào biến dưới dạng chuỗi
//                uploaddata();
//                dialog.dismiss();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                dialog.dismiss();
//
//            }
//        });
//    }
//    //luu du lieu vao firebase
//    public  void uploaddata(String key){
//
//
//        String title = uploadTopic.getText().toString();
//        String desc = uploadDesc.getText().toString();
//        String lang = uploadLang.getText().toString();
//        DataClass dataClass = new DataClass(title, desc, lang, imageURL);
//        //UPdate
//        //chung ta sẽ thay đổi phần tử con từ title thành curentDat
//        //vì chúng tôi sẽ cập nhật tiêu đề và nó có thể ảnh hưởng gia tri con
//        String currentDate = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
//
//        FirebaseDatabase.getInstance().getReference("Android")
//                .child(currentDate).setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(Upload.this, "Saved", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(Upload.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//
//}



package com.example.options1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class Upload extends AppCompatActivity {
    ImageView uploadimg;
    Button saveButton;
    EditText uploadTopic, uploadDesc, uploadLang;
    String imageURL;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        uploadimg = findViewById(R.id.uploadimg);
        uploadDesc = findViewById(R.id.updateDesc);
        uploadTopic = findViewById(R.id.updateTopic);
        uploadLang = findViewById(R.id.updateLang);
        saveButton = findViewById(R.id.savebutton);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadimg.setImageURI(uri);
                        } else {
                            Toast.makeText(Upload.this, "No image selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK);
                photoIntent.setType("image/*");
                activityResultLauncher.launch(photoIntent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri != null) {
                    saveData();
                } else {
                    Toast.makeText(Upload.this, "Please select an image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void saveData() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                .child("Android").child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(Upload.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uri1 = uriTask.getResult();
                imageURL = uri1.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(Upload.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadData() {
        String title = uploadTopic.getText().toString();
        String desc = uploadDesc.getText().toString();
        String lang = uploadLang.getText().toString();
        DataClass dataClass = new DataClass(title, desc, lang, imageURL);

        String currentDate = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Android")
                .child(currentDate).setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Upload.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Upload.this, "Upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

