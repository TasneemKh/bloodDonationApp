package com.example.blooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TabActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private Toolbar mToolbar ;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        String data = getIntent().getStringExtra("map");
        if (data != null && data.contentEquals("1")) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_map);
        }else{

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true ;
    }
    home home=new home();
    map map=new map();
    MapsActivity mapsActivity=new MapsActivity();
    notification notification=new notification();
    HistoryActivity history=new HistoryActivity();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Menu menu = bottomNavigationView.getMenu();

        menu.findItem(R.id.navigation_home).setIcon(R.drawable.ic_home_24px);
        menu.findItem(R.id.navigation_map).setIcon(R.drawable.ic_map);
        menu.findItem(R.id.navigation_notifications).setIcon(R.drawable.ic_notification);
        menu.findItem(R.id.navigation_dashboard).setIcon(R.drawable.ic_reservation);
        //Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                item.setIcon(R.drawable.ic_fill_home_24px);
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .replace(R.id.container, home).commit();
                return true;
            case R.id.navigation_map:
                item.setIcon(R.drawable.ic_fill_history);
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .replace(R.id.container, map).commit();
                return true;
            case R.id.navigation_notifications:
                item.setIcon(R.drawable.ic_fill_notification);
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.fade_in, R.anim.fade_out).
                        replace(R.id.container, notification).commit();
                return true;
            case R.id.navigation_dashboard:
                item.setIcon(R.drawable.ic_fill_reservation);
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.fade_in, R.anim.fade_out).
                        replace(R.id.container, history).commit();
                return true;
        }
        return false;
    }
}
 /*  nav_view = findViewById(R.id.nav_view);
        nav_view.setOnNavigationItemSelectedListener(this);

        nav_view.setSelectedItemId(R.id.navigation_home);*/

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
        /*mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
*/
// to create bottom navigation bar