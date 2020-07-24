package com.example.blooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Add_Request_Activity extends AppCompatActivity /*implements Blood_type_Activity.shareDataInterface */{
NumberPicker noOfUnits;
EditText nOU,bloodType,hospital,patientFileNo,notes;
    TextInputLayout fileNo,bloodTyp,bloodNo,hospitalName;
    DatabaseReference reference;
    String[] arrayPicker= new String[]{"x","xx"};
    String[] arrayPickerType= new String[]{"o positive","o negative","A positive","A negative","B positive","B negative","AB positive","AB negative",};
    private FirebaseAuth mAuth;
    FirebaseUser user;

    // NumberPicker pickers;
    //BottomSheetDialog bottomSheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__request_);
        //bottomSheetDialog=new BottomSheetDialog();
        patientFileNo=findViewById(R.id.patientFileNo);
        nOU=findViewById(R.id.NoOfUnits);
        notes=findViewById(R.id.notes);
        bloodType=findViewById(R.id.bloodType);
        noOfUnits=findViewById(R.id.unitsNumber);
        hospital=findViewById(R.id.hospital);
        //close add request activity
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TabActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!validateFileNumber() | !validateBloodType() | !validateBloodNo() | !validateHospital()) {
                    return;
                }
                //add at firebase
                mAuth = FirebaseAuth.getInstance();
                user = mAuth.getCurrentUser();
                String uid = user.getUid();
                String id= UUID.randomUUID().toString();
                Map<String,Object> data = new HashMap<>();
                String Input = patientFileNo.getText().toString().trim();
                String Input1 = bloodType.getText().toString().trim();
                String Input2 = nOU.getText().toString().trim();
                String Input3 = hospital.getText().toString().trim();
                String Input4 = notes.getText().toString().trim();
                data.put("patientFileNo",Input);
                data.put("bloodType",Input1);
                data.put("numberOfUnits",Input2);
                data.put("hospital",Input3);
                data.put("notes",Input4);
                FirebaseDatabase.getInstance().getReference().child("request").child(uid).child(id).setValue(data)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "on Failuer", Toast.LENGTH_SHORT).show();
                                Log.d("error", e.getLocalizedMessage());
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Your request is added", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Add_Request_Activity.this, TabActivity.class);
                                startActivity(intent);
                            }
                        });
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("Hospitals").child("Gaza");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int t=0;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String Hospital_name = dataSnapshot1.child("Hospital_name").getValue(String.class);
                    System.out.println(Hospital_name);
                    String Type = dataSnapshot1.child("Type").getValue(String.class);
                    if(Type.equals("campaign")) {
                    }else{
                        arrayPicker[t]= Hospital_name;
                        System.out.println(arrayPicker[t]);
                        ++t;
                    }
                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }
        });

        findViewById(R.id.NoOfUnits).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                show1();
            }
        });

        findViewById(R.id.bloodType).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                show2();
            }
        });

        findViewById(R.id.hospital).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                show3();
            }

        });
    }
    private boolean validateFileNumber(){
        fileNo=findViewById(R.id.textField);
        String Input = patientFileNo.getText().toString().trim();
        if (Input.isEmpty()) {
            fileNo.setError("Field can't be empty");
            return false;
        }else {
            fileNo.setError(null);
            return true;
        }
    }
    private boolean validateBloodType(){
        bloodTyp=findViewById(R.id.textField2);
        String Input = bloodType.getText().toString().trim();
        if (Input.isEmpty()) {
            bloodTyp.setError("Field can't be empty");
            return false;
        }else {
            bloodTyp.setError(null);
            return true;
        }
    }
    private boolean validateBloodNo(){
        bloodNo=findViewById(R.id.textField3);
        String Input = nOU.getText().toString().trim();
        if (Input.isEmpty()) {
            bloodNo.setError("Field can't be empty");
            return false;
        }else {
            bloodNo.setError(null);
            return true;
        }
    }
    private boolean validateHospital(){
        hospitalName=findViewById(R.id.textField4);
        String Input = hospital.getText().toString().trim();
        if (Input.isEmpty()) {
            hospitalName.setError("Field can't be empty");
            return false;
        }else {
            hospitalName.setError(null);
            return true;
        }
    }
    public void show1(){
        final AlertDialog.Builder d = new AlertDialog.Builder(Add_Request_Activity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.date_picker, null);
        d.setTitle("Set Number Of Units Needed");
        d.setView(dialogView);
        final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.unitsNumber);
        numberPicker.setMaxValue(30);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
            }
        });
        d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nOU.setText(String.valueOf(numberPicker.getValue()));
            }
        });
        d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = d.create();
        alertDialog.show();

    }
    public void show2() {

        final AlertDialog.Builder d = new AlertDialog.Builder(Add_Request_Activity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.hospitalpicker, null);
        d.setTitle("Set Your blood Type");
        d.setView(dialogView);
        final NumberPicker pickers = (NumberPicker) dialogView.findViewById(R.id.hospitalpicker);
        pickers.setMinValue(0);
        pickers.setMaxValue(arrayPickerType.length - 1);
        pickers.setDisplayedValues(arrayPickerType);
        pickers.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        pickers.setWrapSelectorWheel(true);
        pickers.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
            }
        });
        d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int u =pickers.getValue();
                String selecPicker = arrayPickerType[u];
                bloodType.setText(selecPicker);
            }
        });
        d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = d.create();
        alertDialog.show();

    }
    public void show3(){
        final AlertDialog.Builder d = new AlertDialog.Builder(Add_Request_Activity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.hospitalpicker, null);
        d.setTitle("Set Your hospital or blood bank name");
        d.setView(dialogView);
        final NumberPicker pickers = (NumberPicker) dialogView.findViewById(R.id.hospitalpicker);
        pickers.setMinValue(0);
        pickers.setMaxValue(arrayPicker.length - 1);
        pickers.setDisplayedValues(arrayPicker);
        pickers.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        pickers.setWrapSelectorWheel(true);
        pickers.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
            }
        });
        d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int u =pickers.getValue();
                String selecPicker = arrayPicker[u];
                hospital.setText(selecPicker);
            }
        });
        d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = d.create();
        alertDialog.show();

    }

}
  /*final AlertDialog.Builder d = new AlertDialog.Builder(Add_Request_Activity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_blood_type, null);
        d.setTitle("Set Your Blood Unit Needed");
        d.setView(dialogView);
       // String[] singleChoiceItems={R.id.opositive,R.id.radioOneg};
        final RadioGroup RadioGroup=(RadioGroup)dialogView.findViewById(R.id.radioGroup);
        int itemSelected = 0;
        /*d.setSingleChoiceItems(RadioGroup, itemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                       /* boolean checked = ((RadioButton) view).isChecked();
                        if(case R.id.opositive){
                        if (checked)
                            System.out.println("positive");
                        str = "O pos";}*/
//  dialogInterface.dismiss();
                  /*  }
                });*/
      /*  d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //nOU.setText(String.valueOf(numberPicker.getValue()));
            }
        });
        d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = d.create();
        alertDialog.show();*/