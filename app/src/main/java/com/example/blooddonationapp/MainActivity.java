package com.example.blooddonationapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button signIn,sign_up;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmain);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (mAuth.getCurrentUser()!=null)
        {
            //open main activity
            //it need to  changed
            Intent intent = new Intent(MainActivity.this , TabActivity.class);
            startActivity(intent);
        }
        //it moves to sign up slide
        sign_up=findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), signUp.class);
                startActivity(i);
                finish();
            }
        });

        //it moves to sign in slide
        signIn=findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignIn.class);
                startActivity(i);
                finish();
            }
        });

    }
}
