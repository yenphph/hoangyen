package com.example.yenphph34781_duanmau.frag;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yenphph34781_duanmau.R;
import com.example.yenphph34781_duanmau.adapter.LoaiSachAdapter;
import com.example.yenphph34781_duanmau.dao.LoaiSachDao;
import com.example.yenphph34781_duanmau.model.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frag_loaisach extends Fragment {
    RecyclerView rcv;
    LoaiSachDao dao;
    LoaiSachAdapter adapter;
    ArrayList<LoaiSach> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_loaisach, container, false);
        rcv = view.findViewById(R.id.rcvls);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fltls);
        dao = new LoaiSachDao(getContext());
        list = dao.getDSLoaiSach();
        adapter = new LoaiSachAdapter(getContext(), list);
        rcv.setAdapter(adapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }


    private void show(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.loaisach_up_add, null);
        builder.setView(view);
        ImageView txtup = view.findViewById(R.id.txtLSach_up_add_U);
        ImageView txtadd = view.findViewById(R.id.txtLSach_up_add_A);
        txtup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.item_up_loaisach, null);
                builder1.setView(view1);
                EditText txtma = view1.findViewById(R.id.txtmaLS_U);
                EditText txtten = view1.findViewById(R.id.txttenLS_U);
                Button btnup = view1.findViewById(R.id.update_LS_U);
                Button btnhuy = view1.findViewById(R.id.huyLS_U);
                btnup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = txtten.getText().toString();
                        int ma = Integer.parseInt(txtma.getText().toString());
                        LoaiSach ls = new LoaiSach(ma, ten);
                        boolean kt = dao.capnhatloaisach(ls);
                        if(kt){
                            list.clear();
                            list.addAll(dao.getDSLoaiSach());
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "That bai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                AlertDialog alertDialog = builder1.create();
                alertDialog.show();
            }
        });
        txtadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                LayoutInflater inflater2 = getLayoutInflater();
                View view2 = inflater2.inflate(R.layout.item_add_loaisach, null);
                builder2.setView(view2);
                TextView txtten = view2.findViewById(R.id.txttenLS_A);
                Button btnadd = view2.findViewById(R.id.themLS_A);
                Button btnhuy = view2.findViewById(R.id.huyLS_A);

                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = txtten.getText().toString();

                        boolean kt = dao.themloaisach(ten);
                        if(kt){
                            list.clear();
                            list.addAll(dao.getDSLoaiSach());
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "That bai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                AlertDialog alertDialog = builder2.create();
                alertDialog.show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
