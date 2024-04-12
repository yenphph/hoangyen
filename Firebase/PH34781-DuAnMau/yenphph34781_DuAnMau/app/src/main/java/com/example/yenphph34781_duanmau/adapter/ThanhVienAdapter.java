package com.example.yenphph34781_duanmau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yenphph34781_duanmau.R;
import com.example.yenphph34781_duanmau.dao.ThanhVienDao;
import com.example.yenphph34781_duanmau.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.viewholder> {
    private final Context context;
    private final ArrayList<ThanhVien> list;

    ThanhVienDao dao;
    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ThanhVienAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienAdapter.viewholder holder, int position) {
        holder.txtma.setText("Mã thành viên: " + Integer.toString(list.get(position).getMatv()));
        holder.txttem.setText(list.get(position).getHoten());
        holder.txtnamsinh.setText(list.get(position).getNamsinh());

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Ban co muon xoa?");
                builder.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dao.deleteThanhVien(list.get(holder.getAdapterPosition()).getMatv())){
                            list.clear();
                            list.addAll(dao.getDSThanhVien());
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Da xoa", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txtma , txttem, txtnamsinh;
        ImageView xoa;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtma = itemView.findViewById(R.id.txtmaTV);
            txttem = itemView.findViewById(R.id.txthotenTV);
            txtnamsinh = itemView.findViewById(R.id.txtnamTV);
            xoa = itemView.findViewById(R.id.imgxoatv);
        }
    }

}
