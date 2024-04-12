package com.example.luyentap.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.luyentap.R;
import com.example.luyentap.model.PopularModel;

import java.util.ArrayList;

public class Popular_item extends RecyclerView.Adapter<Popular_item.Viewholder> {
    private final Context context;
    private final ArrayList<PopularModel> listP;
    private OnItemClickListener onItemClickListener;

    public Popular_item(Context context, ArrayList<PopularModel> listP) {
        this.context = context;
        this.listP = listP;
    }

    @NonNull
    @Override
    public Popular_item.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.popular_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Popular_item.Viewholder holder, int position) {
        Glide.with(context).load(listP.get(position).getImg_url()).into(holder.url_img);
        holder.name.setText(listP.get(position).getName());
        holder.des.setText(listP.get(position).getDescription());
        holder.discount.setText(listP.get(position).getDiscount());
        holder.rating.setText(listP.get(position).getRating());
        holder.itemView.setOnClickListener(v -> {
            if(onItemClickListener != null){
                onItemClickListener.onClick(listP.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listP.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView url_img;
        EditText name, des, rating, discount;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            url_img = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.pop_name);
            des = itemView.findViewById(R.id.pop_des);
            rating = itemView.findViewById(R.id.pop_rading);
            discount = itemView.findViewById(R.id.pop_discount);
        }
    }
    public  void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public  interface  OnItemClickListener{
        void onClick(PopularModel popularModel);

    }
}
