package com.example.navigation.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.navigation.R;
import com.example.navigation.modal.MyCartModel;

import java.util.ArrayList;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.Viewholder> {
    private final Context context;
    private final ArrayList<MyCartModel> listM;
    int totalPrice;

    public MyCartAdapter(Context context, ArrayList<MyCartModel> listM) {
        this.context = context;
        this.listM = listM;
    }

    @NonNull
    @Override
    public MyCartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.my_cart_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.Viewholder holder, int position) {
        holder.name.setText(listM.get(position).getProductName());
        holder.price.setText(listM.get(position).getProductPrice());
        holder.date.setText(listM.get(position).getSaveCurrenDate());
        holder.time.setText(listM.get(position).getSaveCurrentTime());
        holder.quantity.setText(listM.get(position).getTotalQuantity());
        holder.totalPrice.setText(Integer.toString(listM.get(position).getTotalPrice()));

        //pass total amount to My cart fragment B1(A)
        totalPrice = totalPrice + listM.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount", totalPrice);
        // b4(A)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


    }

    @Override
    public int getItemCount() {
        return listM.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name, price, date, time, quantity, totalPrice;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price= itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.product_current_date);
            time = itemView.findViewById(R.id.curent_time);
            quantity = itemView.findViewById(R.id.product_total_quantity);
            totalPrice = itemView.findViewById(R.id.product_total_price);
        }
    }
}
