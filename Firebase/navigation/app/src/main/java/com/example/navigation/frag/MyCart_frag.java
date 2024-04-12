package com.example.navigation.frag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.Activity.OrderPlaceActivity;
import com.example.navigation.Activity.PlacedOrderActivity;
import com.example.navigation.R;
import com.example.navigation.adapter.MyCartAdapter;
import com.example.navigation.modal.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyCart_frag extends Fragment {
    RecyclerView recyclerView;
    MyCartAdapter myCartAdapter;
    ArrayList<MyCartModel> listM;
    FirebaseFirestore firebaseFirestore;
    TextView overTotalmount;
    Button buyNow;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mycart_frag, container, false);
        recyclerView = view.findViewById(R.id.rec_mycart);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        buyNow = view.findViewById(R.id.buy_now);
        listM = new ArrayList<>();

        //b2 A (4 dong)
        overTotalmount = view.findViewById(R.id.textView6);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, new IntentFilter("MyTotalAmount"));

        myCartAdapter = new MyCartAdapter(getContext(), listM);
        recyclerView.setAdapter(myCartAdapter);

        firebaseFirestore.collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        MyCartModel myCartModel = documentSnapshot.toObject(MyCartModel.class);
                        listM.add(myCartModel);
                        myCartAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


// Thêm dữ liệu vào collection "AddToCart" của tài liệu không liên quan đến người dùng
//        firebaseFirestore.collection("AddToCart")
//                .document("As1E2iaD22cwtm1dPBPf") // Thay "DocumentID" bằng ID tài liệu mong muốn
//                .collection("CurrentUser")
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            // Xử lý dữ liệu từ collection "CurrentUser"
//                            String productName = document.getString("productName");
//                            String productPrice = document.getString("productPrice");// Thay "fieldName" bằng tên trường dữ liệu cần lấy
//                            String saveCurrenDate = document.getString("saveCurrenDate");// Thay "fieldName" bằng tên trường dữ liệu cần lấy
//                            String saveCurrentTime = document.getString("saveCurrentTime");// Thay "fieldName" bằng tên trường dữ liệu cần lấy
//                            int totalPrice = Integer.parseInt(document.getString("totalPrice"));// Thay "fieldName" bằng tên trường dữ liệu cần lấy
//                            String totalQuantity = document.getString("totalQuantity");// Thay "fieldName" bằng tên trường dữ liệu cần lấy
//
//                            // Tạo đối tượng CartItem từ dữ liệu và thêm vào danh sách
//                            MyCartModel myCartModel = new MyCartModel(productName, productPrice, saveCurrenDate, saveCurrentTime, totalPrice, totalQuantity);
//                            listM.add(myCartModel);
//                            myCartAdapter.notifyDataSetChanged();
//                        }
//
//                        // Ở đây, danh sách cartItemList chứa tất cả các mục trong collection "CurrentUser"
//                        // Bạn có thể sử dụng danh sách này cho các mục đích hiển thị hoặc xử lý khác trong ứng dụng của bạn.
//
//                    }
//                        // Xử lý lỗi khi truy vấn dữ liệu
//
//                });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlacedOrderActivity.class);
                intent.putExtra("itemList", listM);
                startActivity(intent);
            }
        });
        return view;
    }
    //b3 A
    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalBill = intent.getIntExtra("totalAmount", 0);
            overTotalmount.setText("Total Bill: " + totalBill + "$");
        }
    };

}
