package com.example.blooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class updatingPassword extends AppCompatActivity implements View.OnClickListener  {
    TextInputLayout oldpassword,newpassword;
    FirebaseUser user;
    TextInputLayout til;
    TextView email_rec;
    Button recoverPassword;
    ImageButton back;

    private static final String TAG = "update password";
    FirebaseAuth auth;
    String text2;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updating_password);
        til = (TextInputLayout) findViewById(R.id.e_mail);
        recoverPassword=findViewById(R.id.recoverPassword);
        recoverPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                email_rec=findViewById(R.id.e_mail1);
                String emailAddress = email_rec.getText().toString();
                if(!(emailAddress.isEmpty())) {
                    auth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Log.d(TAG, "Email sent.");
                                        Toast.makeText(getApplicationContext(), "Please ,review your email", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(getApplicationContext(), SettingActivity.class);
                                        startActivity(i);
                                    } else {
                                        //  Toast.makeText(getApplicationContext(), "Please ,enter a valid email", Toast.LENGTH_LONG).show();
                                        //  Log.d("MEDIA_PLAYER", new Exception().getMessage());
                                        til.setError("Enter a valid email");
                                        // Toast.makeText(getApplicationContext(), "Please ,enter a valid email", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }else{
                    til.setError("Please ,fill the space");
                }
            }
        });
        back=findViewById(R.id.back);
        back.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                /*change to settings*/
                startActivity(new Intent(this,SettingActivity.class));
                break;

            default:
                break;
        }
    }
}
/*   auth=FirebaseAuth.getInstance();
         user = auth.getCurrentUser();
button=findViewById(R.id.save);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        updatePass();
    }
});
        oldpassword=findViewById(R.id.oldpassword);
        newpassword=findViewById(R.id.newpassword);
        oldpassword.setEndIconActivated(false);
        newpassword.setEndIconActivated(false);
*/
        /*oldpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    oldpassword.setEndIconActivated(true);
                }
            }
        });*/
          /*  public void updatePass (){
        String text=oldpassword.getEditText().getText().toString().trim();
         text2=newpassword.getEditText().getText().toString().trim();
        if(!(text.isEmpty()) && !(text2.isEmpty())){
            if(user != null && user.getEmail() != null){
            AuthCredential credential = EmailAuthProvider
                    .getCredential(user.getEmail(),text2);
System.out.println(user.getEmail());
            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Log.d(TAG, "User re-authenticated.");
                                Toast.makeText(updatingPassword.this, "password updated",
                                        Toast.LENGTH_SHORT).show();
                              auth.signOut();
                                Intent intent = new Intent(updatingPassword.this , SignIn.class);
                                startActivity(intent);

                            }else{

                                Toast.makeText(updatingPassword.this, "User re-authenticated failed",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
            }
        }else{
            Toast.makeText(updatingPassword.this, "Please , Enter are fields",
                    Toast.LENGTH_SHORT).show();

        }
    }*/