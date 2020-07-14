package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Add_Request_Activity extends AppCompatActivity implements View.OnClickListener {
RadioButton radioOpos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__request_);
        findViewById(R.id.close).setOnClickListener(this);
        findViewById(R.id.bloodType).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(Add_Request_Activity.this);
                bottomSheetDialog.setContentView(R.layout.blood_type);

            }
        });
    }
   /* private void addRequest(String email, String password) {


    }*/


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.close:
                startActivity(new Intent(this, TabActivity.class));
                break;
            default:
                break;
        }
    }
}
