package com.example.navigation.Lan1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.R;

import java.util.ArrayList;

public class menuadapter extends RecyclerView.Adapter<menuadapter.Viewholder> {
    private Context context;
    private final ArrayList<menu> listM;

    public menuadapter(Context context, ArrayList<menu> listM) {
        this.context = context;
        this.listM = listM;
    }

    @NonNull
    @Override
    public menuadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return  new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull menuadapter.Viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listM.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
