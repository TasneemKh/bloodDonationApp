package com.example.blooddonationapp.Activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.example.blooddonationapp.R;
import com.example.blooddonationapp.TabActivity;

public class DonationTypeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mBack;
    private Toolbar mToolbar;
    /**
     * Pre-donation Check
     */
    private AppCompatTextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_type);
        initView();
       // mTitle.setText(" ");
    }

    public void privateDonation(View view) {
        startActivity(new Intent(this, DonationPrivateActivity.class));
    }

    public void publicDonation(View view) {
        startActivity(new Intent(this, DonationPublicActivity.class));

    }


    private void initView() {
        mBack =  findViewById(R.id.imageButton2);
        mBack.setOnClickListener(this);
       // mToolbar = (Toolbar) findViewById(R.id.toolbar);
       // mTitle = (AppCompatTextView) findViewById(R.id.title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.imageButton2:
                startActivity(new Intent(this, TabActivity.class));
                break;
        }
    }
}