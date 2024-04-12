package com.example.navigation.activity1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.navigation.R;
import com.google.android.material.navigation.NavigationView;

public class trangchu1 extends AppCompatActivity {
DrawerLayout dra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu2);
        NavigationView nar = findViewById(R.id.nar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout navigationView = findViewById(R.id.framlayout);
        dra = findViewById(R.id.dra);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        nar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
//                if(item.getItemId() ==  R.id.phieumuon){
//                    Fragment fragment = new frag_phieumuon();
//                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
//                }else if (item.getItemId() == R.id.loaisach) {
//                    Fragment fragment = new frag_loaisach();
//                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
//                }else if (item.getItemId() == R.id.taotk) {
//                    Fragment fragment = new taotaikhoan();
//                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
//                }else if (item.getItemId() == R.id.sach) {
//                    Fragment fragment = new frag_sach();
//                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
//                }else if (item.getItemId() == R.id.thanhvien) {
//                    Fragment fragment = new frag_thanhvien();
//                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
//                }else if (item.getItemId() == R.id.matkhau) {
//                    Fragment fragment = new frag_doimk();
//                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
//                }else if (item.getItemId() == R.id.top10) {
//                    Fragment fragment = new frag_top19();
//                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
//                }else if (item.getItemId() == R.id.doanhthu) {
//                    Fragment fragment = new frg_doanhthufragment();
//                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
//                }else if (item.getItemId() == R.id.thoat) {
//                    startActivity(new Intent(MainActivity.this, dangnhap.class));
//                }else{
//                    Fragment fragment = new frag_phieumuon();
//                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
//                }
                dra.closeDrawer(GravityCompat.START);
                setTitle(item.getTitle());
                return false;
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("Thongtin", MODE_PRIVATE);
        String loaiTK = sharedPreferences.getString("loaitaikhoan", "");
        if (!loaiTK.equals("admin")){
            Menu menu = nar.getMenu();
            menu.findItem(R.id.taotk).setVisible(false);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            dra.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}