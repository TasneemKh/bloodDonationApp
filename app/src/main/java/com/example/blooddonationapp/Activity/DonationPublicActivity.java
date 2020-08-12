package com.example.blooddonationapp.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.example.blooddonationapp.Model.Hospital;
import com.example.blooddonationapp.Model.PublicRequest;
import com.example.blooddonationapp.Model.Request;
import com.example.blooddonationapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DonationPublicActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * select one please
     */
    private AppCompatTextView mBloodType;
    /**
     * what is the nearest blood bank for you ?
     */
    private AppCompatTextView mHospital;
    /**
     * what is the suitable Day for you?
     */
    private AppCompatTextView mDatee;
    /**
     * What time is suitable for you?
     */
    private AppCompatTextView mTimee;
    /**
     * Submit
     */
    private Button mPublice;
    private FirebaseAuth mAuth;
    String uid;
    FirebaseUser user;
    DatabaseReference ref;
    FirebaseDatabase database;
    boolean flag = false;
    String f;
    /**
     * Pre-donation Check
     */
    private AppCompatTextView mTitle;
     ImageButton mBack;
    private Toolbar mToolbar;

    List<Hospital> hospitalq = new ArrayList<>();
    private int postions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_public);
        initView();

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        uid = user.getUid();
        ref = database.getReference("Hospitals");
       // mTitle.setText("Donate to the public");
    }

    private void initView() {
        mBloodType = (AppCompatTextView) findViewById(R.id.blood_type);
        mBloodType.setOnClickListener(this);
        mHospital = (AppCompatTextView) findViewById(R.id.hospital);
        mHospital.setOnClickListener(this);
        mDatee = (AppCompatTextView) findViewById(R.id.datee);
        mDatee.setOnClickListener(this);
        mTimee = (AppCompatTextView) findViewById(R.id.timee);
        mTimee.setOnClickListener(this);
        mPublice = (Button) findViewById(R.id.publice);
        mPublice.setOnClickListener(this);
        //mTitle = (AppCompatTextView) findViewById(R.id.title);
        mBack =  (ImageButton) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationPublicActivity.this,DonationTypeActivity.class));
            }
        });
       // mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private boolean Validations() {

        if (TextUtils.isEmpty(mBloodType.getText().toString())) {
            Toast.makeText(this, "please select blood type", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(mHospital.getText().toString())) {
            Toast.makeText(this, "please select Hospital name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(mDatee.getText().toString())) {
            Toast.makeText(this, "please select the date", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(mTimee.getText().toString())) {
            Toast.makeText(this, "please select the time", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                startActivity(new Intent(this,DonationTypeActivity.class));
                break;
            case R.id.blood_type:
                String[] pickerVals = new String[]{"blood", "platelets"};
                flag = false;
                showMyDialog(pickerVals, 1, "Blood Type Picker");
                break;
            case R.id.hospital:
                final List<String> hospitals = new ArrayList<>();
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot s : snapshot.getChildren()) {
                                if (s.hasChildren()) {
                                    for (DataSnapshot snapshot1 : s.getChildren()) {
                                        Hospital hospital = snapshot1.getValue(Hospital.class);
                                        hospitals.add(hospital.getHospital_name());
                                        hospitalq.add(hospital);
                                    }
                                }

                            }
                        }
                        String[] data = new String[hospitals.size()];
                        data = hospitals.toArray(data);
                        flag = true;
                        showMyDialog(data, hospitals.size() - 1, "Hospital Picker");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            case R.id.datee:
                final Calendar myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String strDateFormat = "dd/MM/YYYY";
                        DateFormat dateFormat = new SimpleDateFormat(strDateFormat, new Locale("en"));
                        String formattedDate = dateFormat.format(myCalendar.getTime());

                        mDatee.setText(formattedDate);
                    }
                };
                new DatePickerDialog(this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                break;
            case R.id.timee:
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                mTimee.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                break;
            case R.id.publice:
                String uid = user.getUid();
                DatabaseReference ref2 = database.getReference("User").child(uid);
                ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot s : snapshot.getChildren()) {
                            f= snapshot.child("userName").getValue(String.class);
                            Toast.makeText(DonationPublicActivity.this, " "+f, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                if (Validations()) {

                    DatabaseReference ref1 = database.getReference("reqDonation").
                            child(hospitalq.get(postions).getId()).child(uid).push();
                    String id = ref1.getKey();
                    Request request = new PublicRequest(mDatee.getText().toString(), mTimee.getText().toString(),
                            mBloodType.getText().toString(), mHospital.getText().toString(), "public",
                            uid, id,f);
                    Toast.makeText(DonationPublicActivity.this, " "+f, Toast.LENGTH_SHORT).show();

                    ref1.setValue(request)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(DonationPublicActivity.this, "add successfully", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                }
                            });

                }

                break;
        }
    }

    private void showMyDialog(final String[] pickerVals, int i, String titles) {
        final Dialog dialog = new Dialog(this, R.style.mydialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_piker);
        final NumberPicker picker1 = dialog.findViewById(R.id.numberpicker_main_picker);
        final AppCompatTextView title = dialog.findViewById(R.id.title);
        AppCompatImageButton close = dialog.findViewById(R.id.close);

        title.setText(titles);

        picker1.setMaxValue(i);
        picker1.setMinValue(0);

        picker1.setDisplayedValues(pickerVals);

        picker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker1 = picker1.getValue();
                if (flag) {
                    mHospital.setText(pickerVals[valuePicker1]);
                } else {
                    mBloodType.setText(pickerVals[valuePicker1]);
                }
                // dialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}