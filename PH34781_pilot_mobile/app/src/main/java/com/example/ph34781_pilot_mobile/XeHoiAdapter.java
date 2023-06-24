package com.example.ph34781_pilot_mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class XeHoiAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<XeHoi> list;

    public XeHoiAdapter(Context context, ArrayList<XeHoi> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_list,parent,false);
        TextView txtten = convertView.findViewById(R.id.txtten);
        TextView txthang = convertView.findViewById(R.id.txthang);
        TextView txtnam = convertView.findViewById(R.id.txtnam);
        TextView txtgia = convertView.findViewById(R.id.txtgia);

        txtten.setText(list.get(position).getTen());
        txthang.setText(list.get(position).getHangSX());
        txtnam.setText(Integer.toString(list.get(position).getNamSX()));
        txtgia.setText(Double.toString(list.get(position).getGiaBan()));

        XeHoi xhm = list.get(position);
        Button btnxoa = convertView.findViewById(R.id.btnxoa);
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(xhm);
            }
        });

        return convertView;
    }
    public void openDialog(XeHoi xh){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_xoa,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        Button btnco = view.findViewById(R.id.btnco);

        btnco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 list.remove(xh);
                notifyDataSetChanged();
                file.writeFile(context,"FileCar.txt", list);
                dialog.dismiss();
            }
        });
        Button btnkh = view.findViewById(R.id.btnkh);
        btnkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
