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
import com.example.navigation.Activity.ViewAllMainActivity;
import com.example.navigation.R;
import com.example.navigation.modal.HomeCategory;

import java.util.ArrayList;
//explor product
public class Homeadapter extends RecyclerView.Adapter<Homeadapter.viewholder> {
    private final Context context;
    private final ArrayList<HomeCategory> listH;

    public Homeadapter(Context context, ArrayList<HomeCategory> listH) {
        this.context = context;
        this.listH = listH;
    }

    @NonNull
    @Override
    public Homeadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.home_cat_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Homeadapter.viewholder holder, int position) {
        Glide.with(context).load(listH.get(position).getImg_url()).into(holder.catImg);
        holder.name.setText(listH.get(position).getName());

        //nhan vao item thi se hien ra ViewAllAdapter
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllMainActivity.class);
                intent.putExtra("type", listH.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listH.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
       ImageView catImg;
       TextView name;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            catImg = itemView.findViewById(R.id.home_cat_img);
            name = itemView.findViewById(R.id.cat_home_name);
        }
    }
}
