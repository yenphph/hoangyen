package com.example.luyentap.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.luyentap.R;
import com.example.luyentap.model.HomeCategory;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Viewholder>{
    private final Context context;
    private final ArrayList<HomeCategory> listH;

    public HomeAdapter(Context context, ArrayList<HomeCategory> listH) {
        this.context = context;
        this.listH = listH;
    }


    @NonNull
    @Override
    public HomeAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.home_cat_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.Viewholder holder, int position) {
        Glide.with(context).load(listH.get(position).getImg_url()).into(holder.caImg);
        holder.name.setText(listH.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listH.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView caImg;
        TextView name;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            caImg = itemView.findViewById(R.id.homePopularImg);
            name = itemView.findViewById(R.id.homePopularName);
        }
    }
}