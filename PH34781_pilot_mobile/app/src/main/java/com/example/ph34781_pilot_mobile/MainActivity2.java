package com.example.ph34781_pilot_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
private Context context = MainActivity2.this;
ArrayList<XeHoi> list = new ArrayList<>();
ListView lstds;
XeHoiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lstds = findViewById(R.id.lstds);
        adapter = new XeHoiAdapter(context, list);
//        dataFake();
        ArrayList<XeHoi> readds = new ArrayList<>();
        readds.clear();
        readds = (ArrayList<XeHoi>) file.readFile(context, "file.txt");
        list.addAll(readds);
        lstds.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Button btnadd = findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity3.class));
            }
        });

    }
    public void dataFake(){
        list.add(new XeHoi("HondaBrio", "Honda", 2021, 418000000));
        list.add(new XeHoi("Infiniti QX70", "Infiniti", 2020, 43890000));
        list.add(new XeHoi("Noble M500", "Noble", 2022, 41000000));
        list.add(new XeHoi("Nissan", "Nissan", 2023, 618000000));
        list.add(new XeHoi("Morgan Plus Six", "Morgan", 2021, 88800000));

        file.writeFile(context, "file.txt", list);
    }
}