package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {
    private static int SPLASH_TIME = 1000; //This is 1 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    //Do any action here. Now we are moving to next page
                    Intent mySuperIntent = new Intent(WelcomeActivity.this, SplashMain.class);
                    startActivity(mySuperIntent);

                    //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                    finish();

                }
            }, SPLASH_TIME);
        } else {
            // Swap without transition
        }

    }

}
