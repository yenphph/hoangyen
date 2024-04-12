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
import com.example.yenphph34781_duanmau.adapter.ThanhVienAdapter;
import com.example.yenphph34781_duanmau.dao.ThanhVienDao;
import com.example.yenphph34781_duanmau.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frag_thanhvien extends Fragment {
    ThanhVienDao dao;
    ArrayList<ThanhVien> list;
    ThanhVienAdapter adapter;
    RecyclerView rcv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_thanhvien, container, false);
        rcv = view.findViewById(R.id.rcvtv);
        FloatingActionButton fl = view.findViewById(R.id.flttv);
        dao = new ThanhVienDao(getContext());
        list = dao.getDSThanhVien();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        adapter = new ThanhVienAdapter(getContext(), list);
        rcv.setAdapter(adapter);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }
    private void showDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.thanhvien_add_up, null);
        builder.setView(view);
        ImageView txtup = view.findViewById(R.id.txttv_up_add_U);
        ImageView txtadd = view.findViewById(R.id.txttv_up_add_A);
        txtadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.item_add_thanhvien, null);
                builder1.setView(view1);
                EditText txtten = view1.findViewById(R.id.txthotenTV_A);
                EditText txtnam = view1.findViewById(R.id.txtnamsinhTV_A);
                Button btnadd = view1.findViewById(R.id.themTV_A);
                Button btnhuy = view1.findViewById(R.id.huyTV_A);
                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = txtten.getText().toString();
                        String nam = txtnam.getText().toString();

                        boolean kt = dao.themthanhvien(ten, nam);
                        if(kt){
                            list.clear();
                            list.addAll(dao.getDSThanhVien());
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
        txtup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                LayoutInflater inflater2 = getLayoutInflater();
                View view2 = inflater2.inflate(R.layout.item_up_thanhvien, null);
                builder2.setView(view2);
                TextView txtma = view2.findViewById(R.id.txtmaTV_U);
                EditText txtten = view2.findViewById(R.id.txthotenTV_U);
                EditText txtnam = view2.findViewById(R.id.txtnamsinhTV_U);
                Button btnup = view2.findViewById(R.id.updateTV_U);
                Button btnhuy = view2.findViewById(R.id.huyTV_U);


                btnup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = txtten.getText().toString();
                        String nam = txtnam.getText().toString();
                        int id = Integer.parseInt(txtma.getText().toString());
                        ThanhVien tv = new ThanhVien(id, ten, nam);
                        boolean kt = dao.capnhatthanhvien(tv);
                        if(kt){
                            list.clear();
                            list.addAll(dao.getDSThanhVien());
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
