package com.example.blooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TabActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView nav_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setOnNavigationItemSelectedListener(this);

        nav_view.setSelectedItemId(R.id.navigation_home);

           /* when(item.itemId) {
                R.id.navigation_home -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.navigation_map -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.navigation_notifications -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.navigation_dashboard -> {
                    // Respond to navigation item 2 click
                    true
                }
            }
        };*/
    }
    home home=new home();
    map map=new map();
    MapsActivity mapsActivity=new MapsActivity();
    notification notification=new notification();
    HistoryActivity history=new HistoryActivity();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()){
            case R.id.navigation_home:
                nav_view.setItemIconTintList(ColorStateList.valueOf(Color.parseColor("#043353")));

                getSupportFragmentManager().beginTransaction().replace(R.id.container,home).commit();
                return true;

            case R.id.navigation_map :
                getSupportFragmentManager().beginTransaction().replace(R.id.container, map).commit();
                return true;

            case R.id.navigation_notifications:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,notification).commit();
                return true;

            case R.id.navigation_dashboard :
                getSupportFragmentManager().beginTransaction().replace(R.id.container,history).commit();
                return true;
        }
        return false;
    }
}