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
import com.example.navigation.modal.NavCategoryModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NavCategoryadapter extends RecyclerView.Adapter<NavCategoryadapter.viewholder> {
    private final Context context;
    private final ArrayList<NavCategoryModel> listN;

    public NavCategoryadapter(Context context, ArrayList<NavCategoryModel> listN) {
        this.context = context;
        this.listN = listN;
    }

    @NonNull
    @Override
    public NavCategoryadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.nav_cat_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryadapter.viewholder holder, int position) {
        Glide.with(context).load(listN.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(listN.get(position).getName());
        holder.discount.setText(listN.get(position).getDiscount());
        holder.description.setText(listN.get(position).getDescription());
        //Tai sao lai itemView getType o ben khong ra chi tiet
        // (co le la an vao trang nay no ra trang chi tiet
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NavCategory.class);
                intent.putExtra("type", listN.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listN.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, description, discount;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cat_nav_img);
            name = itemView.findViewById(R.id.nav_cat_name);
            description= itemView.findViewById(R.id.nav_cat_description);
            discount= itemView.findViewById(R.id.nav_cat_discount);
        }
    }
}
