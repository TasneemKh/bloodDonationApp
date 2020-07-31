package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PreActivity2 extends AppCompatActivity {
    ImageButton yes,no,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre2);
        yes=findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DrugActivityCheck.class);
                startActivity(i);
                finish();
            }
        });
        no=findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), adviceSplash.class);
                startActivity(i);
                finish();
            }
        });
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PreActivity1.class);
                startActivity(i);
                finish();
            }
        });
    }
}