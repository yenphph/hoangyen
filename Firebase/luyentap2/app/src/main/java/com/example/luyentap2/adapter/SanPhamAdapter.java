package com.example.luyentap2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.luyentap2.R;
import com.example.luyentap2.model.SanPham;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.Viewholder> {
    private Context context;
    private final ArrayList<SanPham> list;

    public SanPhamAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SanPhamAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.Viewholder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.anh);
        holder.tensp.setText(list.get(position).getTenSaPham());
        holder.gia.setText(Integer.toString(list.get(position).getGia()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView anh;
        TextView tensp, gia;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            anh = itemView.findViewById(R.id.imgsanpham_Home);
            tensp = itemView.findViewById(R.id.tensanpham_home);
            gia = itemView.findViewById(R.id.txtgia_Home);
        }
    }
}
