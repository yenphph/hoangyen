package com.example.options1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewholder> {
    private final Context context;
    private ArrayList<DataClass> list;

    public MyAdapter(Context context, ArrayList<DataClass> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.viewholder holder, int position) {

        //them
        DataClass dataClass = list.get(position);

        Glide.with(context).load(list.get(position).getDataImage()).into(holder.recImage);

        holder.recTitle.setText(list.get(position).getDataTitle());
        holder.recDesc.setText(list.get(position).getDataDesc());
        holder.recLang.setText(list.get(position).getDataLang());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detail.class);
                //them ne
                Bundle bundle = new Bundle();
                // (xoa di nhe) them du lieu vao boi key
                String key = dataClass.getDataTitle();
                bundle.putString("key", key);
                bundle.putString("image", dataClass.getDataImage());
                bundle.putString("description", dataClass.getDataDesc());
                bundle.putString("title", dataClass.getDataTitle());
                bundle.putString("Language", dataClass.getDataLang());
                intent.putExtras(bundle);


//                bundle.putString("image", list.get(holder.getAdapterPosition()).getDataImage());
//                intent.putExtra("description", list.get(holder.getAdapterPosition()).getDataDesc());
//                intent.putExtra("title", list.get(holder.getAdapterPosition()).getDataTitle());
////                xoa
//                intent.putExtra("key", list.get(holder.getAdapterPosition()).getKey());
////                cap mhat
//                intent.putExtra("Language", list.get(holder.getAdapterPosition()).getDataLang());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void searchDataList (ArrayList<DataClass> searchList){
        list = searchList;
        notifyDataSetChanged();
    }
    public class viewholder extends RecyclerView.ViewHolder {
        ImageView recImage;
        TextView recTitle, recDesc, recLang;
        CardView recCard;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            recImage = itemView.findViewById(R.id.recImage);
            recTitle = itemView.findViewById(R.id.recTitle);
            recDesc = itemView.findViewById(R.id.recdescr);
            recLang = itemView.findViewById(R.id.recLang);
            recCard = itemView.findViewById(R.id.reccard);
        }
    }
}
