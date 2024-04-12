package com.example.buoi1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class useradapter extends RecyclerView.Adapter<useradapter.viewholder> {
    private final Context context;
    private final ArrayList<user> list;
//    ArrayList<user> list;

    public useradapter(Context context, ArrayList<user> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public useradapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.userhome,parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull useradapter.viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txtten, txtemail, txtdatnuoc;
        Button btnDelete, btnUp;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.textname);
            txtemail = itemView.findViewById(R.id.textdatnuoc);
            txtdatnuoc = itemView.findViewById(R.id.txtemail);
             btnUp = itemView.findViewById(R.id.btnupdate);
            btnDelete = itemView.findViewById(R.id.btndelete);
        }
    }
}
