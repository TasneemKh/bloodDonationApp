package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ContactUsActivity extends AppCompatActivity {
    ImageButton returnButton ;
    MaterialButton send ;
    TextInputEditText message ;
    int count = 1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        returnButton = findViewById(R.id.back_arrow);
        send = findViewById(R.id.send);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = findViewById(R.id.message);
                String sendedMessage = message.getText().toString();
                if(sendedMessage.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter Message",Toast.LENGTH_LONG).show();
                }else{

                    Boolean isSend=sendMessageToEmail(sendedMessage);
                    String timeStamp = new SimpleDateFormat("dd/MM HH:mm").format(Calendar.getInstance().getTime());
                    saveMessage(sendedMessage ,timeStamp);
                    if(isSend) {
//                        Intent intent = new Intent(getApplicationContext(), RespondActivity.class);
//                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Message doesn't send",Toast.LENGTH_LONG).show();

                    }
                }
            }
        });


    }
    public boolean sendMessageToEmail(String sendedMessage){
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"hanayoussef.1571997@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Issues About Blood Donation App");
        i.putExtra(Intent.EXTRA_TEXT   , sendedMessage);
        i.setData(Uri.parse("mailto:"));
        try {
            startActivity(Intent.createChooser(i, "Send an email"));
            return true;
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void saveMessage(String sendedMessage ,String timeStamp){

        FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child("contactUs").child(Integer.toString(count)).child("message").setValue(sendedMessage);
        firebaseDatabase.getReference().child("contactUs").child(Integer.toString(count)).child("createdAt").setValue(timeStamp);
        count++;
    }
}