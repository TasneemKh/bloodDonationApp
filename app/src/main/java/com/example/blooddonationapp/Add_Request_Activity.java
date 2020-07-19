package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class Add_Request_Activity extends AppCompatActivity  {
RadioButton radioOpos;
NumberPicker noOfUnits;
EditText nOU;
    //BottomSheetDialog bottomSheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__request_);
        //bottomSheetDialog=new BottomSheetDialog();
nOU=findViewById(R.id.NoOfUnits);
        noOfUnits=findViewById(R.id.unitsNumber);

        //close add request activity
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TabActivity.class);
                startActivity(i);
            }
        });
        //open bottom sheet dialog for blood donation
     /*   findViewById(R.id.bloodType).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(Add_Request_Activity.this);
                bottomSheetDialog.setContentView(R.layout.blood_type);
                //setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);

            }
        });*/
        findViewById(R.id.bloodType).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
              BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(Add_Request_Activity.this);
              bottomSheetDialog.setContentView(R.layout.blood_type);
              bottomSheetDialog.show();
            }
        });
        findViewById(R.id.NoOfUnits).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog2=new BottomSheetDialog(Add_Request_Activity.this);
                bottomSheetDialog2.setContentView(R.layout.date_picker);
                bottomSheetDialog2.show();
            }
               /*Intent i = new Intent(getApplicationContext(), datePicker.class);
               startActivity(i);*/
        });
    }





}
