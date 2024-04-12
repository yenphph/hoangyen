package com.example.yenphph34781_duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yenphph34781_duanmau.R;
import com.example.yenphph34781_duanmau.dao.SachDao;
import com.example.yenphph34781_duanmau.model.Sach;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.holder> {
    private final Context context;
    private final ArrayList<Sach> list;
    SachDao dao;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SachAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.holder holder, int position) {
        holder.txtmasach.setText( "Mã sách: " + String.valueOf(list.get(position).getMasach()));
        holder.txttensach.setText(list.get(position).getTensach());
        holder.txtgiathue.setText(Integer.toString(list.get(position).getGiathue()));
        holder.txtmaloai.setText(Integer.toString(list.get(position).getMaloai()));
        holder.txttenloai.setText(list.get(position).getTenloai());
        dao = new SachDao(context);
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dao.deletesach(list.get(holder.getAdapterPosition()).getMasach())){
                    list.clear();
                    list.addAll(dao.getDSSach());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView txtmasach, txttensach,txtgiathue, txtmaloai, txttenloai;
        ImageView xoa;
        public holder(@NonNull View itemView) {
            super(itemView);
            txtmasach= itemView.findViewById(R.id.txtmasach_S);
            txttensach= itemView.findViewById(R.id.txttensach_S);
            txtgiathue= itemView.findViewById(R.id.txtgiathue_S);
            txtmaloai= itemView.findViewById(R.id.txtloaisach_S);
            txttenloai= itemView.findViewById(R.id.txttenloai_S);
            xoa = itemView.findViewById(R.id.xoasach);
        }
    }
}
