package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class CheckEmail extends AppCompatActivity {
    Button GoToEmail;
    ImageButton close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_email);
        //open email
        GoToEmail=findViewById(R.id.GoToEmail);
        GoToEmail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    Intent mailClient = new Intent(Intent.ACTION_VIEW);
                    mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivityGmail");
                    startActivity(mailClient); }
                catch (Exception e) {
                    Toast.makeText(CheckEmail.this,"Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //close check email activity and return to signin
        close=findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(CheckEmail.this, SignIn.class));
                }catch (Exception e) {
                    Toast.makeText(CheckEmail.this,"Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
