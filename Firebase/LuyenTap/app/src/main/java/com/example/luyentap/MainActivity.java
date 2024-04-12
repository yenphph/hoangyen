package com.example.luyentap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.luyentap.frag.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout dra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dra = findViewById(R.id.dra);
        NavigationView nar = findViewById(R.id.nar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        FrameLayout frameLayout = findViewById(R.id.frame);

        nar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                if(item.getItemId() ==  R.id.home){
                    Fragment fragment = new HomeFragment();
                    fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
//                }else if (item.getItemId() == R.id.profile) {
//                    Fragment fragment = new froFileFrag();
//                    fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
//                }else if (item.getItemId() == R.id.catagory) {
//                    Fragment fragment = new Category_Frag();
//                    fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
//                }else if (item.getItemId() == R.id.orders) {
//                    Fragment fragment = new MyOrdersFrag();
//                    fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
//                }else if (item.getItemId() == R.id.products) {
//                    Fragment fragment = new NewProductsFrag();
//                    fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
//                }else if (item.getItemId() == R.id.carts) {
//                    Fragment fragment = new MyCart_frag();
//                    fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
//                }else if (item.getItemId() == R.id.top10) {
//                    Fragment fragment = new frag_top19();
//                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
//                }else if (item.getItemId() == R.id.doanhthu) {
//                    Fragment fragment = new frg_doanhthufragment();
//                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
//                }else if (item.getItemId() == R.id.thoat) {
//                    startActivity(new Intent(MainActivity.this, dangnhap.class));
                }else{
                    Fragment fragment = new HomeFragment();
                    fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
                }
                dra.closeDrawer(GravityCompat.START);
                setTitle(item.getTitle());
                return false;

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            dra.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}