package com.example.blooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    private EditText emailTxt,passTxt;
    float x1,y1,x2,y2;
    Button signIn;
    TextInputLayout email,pass;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    Boolean isFirstTimeGetFocused,isFirstTimeGetFocused1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // isFirstTimeGetFocused=true;        isFirstTimeGetFocused1=true;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (mAuth.getCurrentUser()!=null)
        {
            //open main activity
            //it need to  changed
            Intent intent = new Intent(SignIn.this , TabActivity.class);
            intent.putExtra("map", " ");
            startActivity(intent);
        }
        initializeUI();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.forgotPassword).setOnClickListener(this);
        findViewById(R.id.movSignUp1).setOnClickListener(this);

    }

    private void loginUserAccount() {
        if (!validateEmail() | !validatePassword()) {
            return;
        }
        String email, password;
        email = emailTxt.getText().toString();
        password = passTxt.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //change it into main activity
                            Intent intent = new Intent(SignIn.this, TabActivity.class);
                            intent.putExtra("map", " ");
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
    private boolean validateEmail() {
        email=(TextInputLayout) findViewById(R.id.e_mail);
        String emailInput = emailTxt.getText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("email can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        }else {
            email.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        pass=(TextInputLayout) findViewById(R.id.password);
        String passwordInput = passTxt.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            pass.setError("password can't be empty");
            return false;
        }else {
            pass.setError(null);
            return true;
        }
    }
    private void initializeUI() {
        emailTxt =findViewById(R.id.emailEditTxt);
        passTxt = findViewById(R.id.passEditTxt);
        signIn = findViewById(R.id.signIn);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, forgetPassword.class));
                break;
            case R.id.movSignUp1:
                startActivity(new Intent(this, signUp.class));
                break;
            case R.id.signIn:
                loginUserAccount();
                break;
            default:
                break;
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch(touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1=touchEvent.getX();
                y1=touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2=touchEvent.getX();
                y2=touchEvent.getY();
                if(x1 < x2){
                    Intent i= new Intent(SignIn.this,MainActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}
