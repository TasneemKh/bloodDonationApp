package com.example.blooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class signUp extends AppCompatActivity implements View.OnClickListener  {
    DatePickerDialog picker;
    EditText birthTxt,emailTxt,passwordTxt,userName;
    private FirebaseAuth mAuth;
    private Button regBtn;
    TextInputLayout email,name,password,birth;
    //private int mYear, mMonth, mDay, mHour, mMinute;
    private static final String TAG = signUp.class.getSimpleName();
    float x1,y1,x2,y2;
    int year;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        birthTxt= findViewById(R.id.eText);
        // birthTxt.setOnClickListener(new View.OnClickListener() {);
        birthTxt.setInputType(InputType.TYPE_NULL);
        birthTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(signUp.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                birthTxt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }

                        }, year, month, day);

                picker.show();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        userName= findViewById(R.id.username);
        emailTxt = findViewById(R.id.emailAddress);
        passwordTxt = findViewById(R.id.passTxt);

        email=findViewById(R.id.e_mail);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        birth=findViewById(R.id.birthday);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.signIn).setOnClickListener(this);
        regBtn = findViewById(R.id.sign_up);
        regBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validateUsername() | !validatePassword() | !validateBirthOfDate()) {
                    return;
                }
                doSignUp(emailTxt.getText().toString().trim(), passwordTxt.getText().toString().trim());
            }
        });
    }

    private boolean validateEmail() {
        email=findViewById(R.id.e_mail);
        String emailInput = email.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }
    private boolean validateUsername() {
        String usernameInput = name.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            name.setError("Field can't be empty");
            return false;
        } /*else if (usernameInput.length() > 25) {
            name.setError("Username too long");
            return false;
            Pattern.compile("^(?=.*[a-zA-Z가-힣])[a-zA-Z가-힣]{1,}$").matcher(usernameInput).matches();
        } */
        //   final String LASTNAME_PATTERN = "[ .a-zA-Z]+";
        //    pattern = Pattern.compile("[ .a-zA-Z]+").matcher(usernameInput).matches()
        //    matcher = pattern.matcher(lasname);
        else if (!Pattern.compile("[ .a-zA-Z]+").matcher(usernameInput).matches()) {
            name.setError("Please enter a valid name");
            return false;
        }
        else {
            name.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = password.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password must contain a-z,A-Z and digits");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
    private boolean validateBirthOfDate() {
        String birthText = birthTxt.getText().toString().trim();
        if (birthText.isEmpty()) {
            birth.setError("Field can't be empty");
            return false;
        } /*else if (!((Calendar.getInstance().get(Calendar.YEAR)-year) > 16)) {
            birth.setError("you need to be +16 years ");
            return false;
        } */else {
            birth.setError(null);
            return true;
        }
    }
    private void doSignUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String emailF = user.getEmail();
                            String uid = user.getUid();
                            Map<String,Object> data = new HashMap<>();
                            data.put("userName",userName.getText().toString().trim());
                            data.put("birthday",birthTxt.getText().toString().trim());
                            data.put("uid",uid);
                            data.put("email",emailF);
                            data.put("createdAt",new Date().getTime());
                            FirebaseDatabase.getInstance().getReference().child("User").child(uid).setValue(data)
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(),"on Failuer", Toast.LENGTH_SHORT).show();
                                            Log.d("error",e.getLocalizedMessage());
                                        }
                                    })
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Intent intent = new Intent(signUp.this , TabActivity.class);
                                            intent.putExtra("map", " ");
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(signUp.this, task.getException().getLocalizedMessage(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }
    private void updateUI(FirebaseUser currentUser) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.signIn:
                startActivity(new Intent(this,SignIn.class));
                break;
            case R.id.sign_up:
                doSignUp(emailTxt.getText().toString(), passwordTxt.getText().toString());
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
                    Intent i= new Intent(signUp.this,MainActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}
   /*
     Date current = newDate.getTime();
             int diff1 =new Date().compareTo(current);

                if(diff1<0){
                    Toast.makeText(getActivity(), "Please select a valid date",  Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    et_dob.setText(dateFormatter.format(newDate.getTime()));
                }
   private void validData(){
        if (isEmpty(userName) | isEmpty(emailTxt) | isEmpty(passwordTxt) |isEmpty(birthTxt)) {
            Snackbar.make(regBtn, "please fill out all required fields ", Snackbar.LENGTH_LONG).show();
            isValid=false;
        }
        if(isEmail(emailTxt)){
           String strPattern="[a-zA-Z.\\s]+ -";
            if ( userName.getText().toString().trim().matches(strPattern)) {
                isValid = true;}
            else{
                userName.setError("username contains letters,or dot or whitespaces.");
                isValid=false;
            }
        }else {
            // R.id.name.error = "Enter valid email";
            emailTxt.setError("Enter valid email!");
            isValid=false;
        }

    }
/*
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
*/


    /*
         Date current = newDate.getTime();
                 int diff1 =new Date().compareTo(current);

                    if(diff1<0){
                        Toast.makeText(getActivity(), "Please select a valid date",  Toast.LENGTH_LONG).show();
                        return;
                    }
                    else{
                        et_dob.setText(dateFormatter.format(newDate.getTime()));
                    }
       private void validData(){
            if (isEmpty(userName) | isEmpty(emailTxt) | isEmpty(passwordTxt) |isEmpty(birthTxt)) {
                Snackbar.make(regBtn, "please fill out all required fields ", Snackbar.LENGTH_LONG).show();
                isValid=false;
            }
            if(isEmail(emailTxt)){
               String strPattern="[a-zA-Z.\\s]+ -";
                if ( userName.getText().toString().trim().matches(strPattern)) {
                    isValid = true;}
                else{
                    userName.setError("username contains letters,or dot or whitespaces.");
                    isValid=false;
                }
            }else {
                // R.id.name.error = "Enter valid email";
                emailTxt.setError("Enter valid email!");
                isValid=false;
            }

        }
    /*
        boolean isEmpty(EditText text) {
            CharSequence str = text.getText().toString();
            return TextUtils.isEmpty(str);
        }
        boolean isEmail(EditText text) {
            CharSequence email = text.getText().toString();
            return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        }
    */
  /*  @Override
    public void onClick(View view) {
        if (view == birthTxt) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            birthTxt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

    }*/