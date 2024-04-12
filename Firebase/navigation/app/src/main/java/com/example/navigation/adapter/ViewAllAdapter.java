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
import com.example.navigation.Activity.DetailedActivity;
import com.example.navigation.R;
import com.example.navigation.modal.ViewAllModel;

import java.util.ArrayList;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.Viewholder> {
    private final Context context;
    private final ArrayList<ViewAllModel> listV;

    public ViewAllAdapter(Context context, ArrayList<ViewAllModel> listV) {
        this.context = context;
        this.listV = listV;
    }

    @NonNull
    @Override
    public ViewAllAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.view_all_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapter.Viewholder holder, int position) {
        Glide.with(context).load(listV.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(listV.get(position).getName());
        holder.description.setText(listV.get(position).getDescription());
        holder.price.setText(Integer.toString(listV.get(position).getPrice()) + "/kg");
        holder.rating.setText(listV.get(position).getRating());
        if (listV.get(position).getType().equals("eggs")){
            holder.price.setText(Integer.toString(listV.get(position).getPrice()) + "/dozen");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                //cho class ViewAllModel cais implemenent Se... thi cai nay hat bao do
                intent.putExtra("detail", listV.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listV.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, description, price, rating;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.view_img);
            name = itemView.findViewById(R.id.view_name);
            description = itemView.findViewById(R.id.view_description);
            price = itemView.findViewById(R.id.view_price);
            rating = itemView.findViewById(R.id.view_rating);


        }
    }
}
