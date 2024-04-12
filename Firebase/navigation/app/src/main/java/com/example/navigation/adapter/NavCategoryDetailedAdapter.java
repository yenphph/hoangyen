package com.example.navigation.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.navigation.Activity.NavCategory;
import com.example.navigation.R;
import com.example.navigation.modal.NavCategoryDetailedModel;

import java.util.ArrayList;

public class NavCategoryDetailedAdapter extends RecyclerView.Adapter<NavCategoryDetailedAdapter.Viewholder> {
    private final Context context;
    private final ArrayList<NavCategoryDetailedModel> listC;

    public NavCategoryDetailedAdapter(Context context, ArrayList<NavCategoryDetailedModel> listC) {
        this.context = context;
        this.listC = listC;
    }

    @NonNull
    @Override
    public NavCategoryDetailedAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.nar_catgory_detailed_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryDetailedAdapter.Viewholder holder, int position) {
        Glide.with(context).load(listC.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(listC.get(position).getName());
        holder.price.setText(Integer.toString(listC.get(position).getPrice()));


    }

    @Override
    public int getItemCount() {
        return listC.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cat_nav_de_img);
            name = itemView.findViewById(R.id.nav_cat_de_name);
            price = itemView.findViewById(R.id.nav_cat_de_price);
        }
    }
}
