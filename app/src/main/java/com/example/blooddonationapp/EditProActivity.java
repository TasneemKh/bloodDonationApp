package com.example.blooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.blooddonationapp.Activity.DonationPublicActivity;
import com.example.blooddonationapp.Activity.DonationTypeActivity;
import com.example.blooddonationapp.Activity.PreDonationCheckActivity;
import com.example.blooddonationapp.Model.Hospital;
import com.example.blooddonationapp.Model.PublicRequest;
import com.example.blooddonationapp.Model.Request;
import com.example.blooddonationapp.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.paperdb.Paper;

public class EditProActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatTextView birthday,blood_type,gender;
    AppCompatEditText fullname,Weight,phone,email;
    ImageButton back,addImg;
    boolean flag = false;
    private FirebaseAuth mAuth;
    String uid;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference ref;
    String x1,x2,x3,x4,x5,x6;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;

    Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pro);
        initView();
    }
    private void initView() {
        fullname = (AppCompatEditText) findViewById(R.id.fullname);
        email = (AppCompatEditText) findViewById(R.id.email);
        birthday = (AppCompatTextView) findViewById(R.id.birthday);
        birthday.setOnClickListener(this);
        blood_type = (AppCompatTextView) findViewById(R.id.blood_type);
        blood_type.setOnClickListener(this);
        gender = (AppCompatTextView) findViewById(R.id.gender);
        gender.setOnClickListener(this);
        Weight = (AppCompatEditText) findViewById(R.id.Weight);
        phone = (AppCompatEditText) findViewById(R.id.phone);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        System.out.println(uid);
        uid = user.getUid();
        Toast.makeText(EditProActivity.this, uid, Toast.LENGTH_SHORT).show();

        FirebaseDatabase.getInstance().getReference().child("User").child(uid)
        .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                   x1=dataSnapshot.child("userName").getValue(String.class);  fullname.setText(x1);
                   x2=dataSnapshot.child("email").getValue(String.class);        email.setText(x2);

                    x3=dataSnapshot.child("birthday").getValue(String.class);
                    x4=dataSnapshot.child("phoneNumber").getValue(String.class);
                    x5=dataSnapshot.child("gender").getValue(String.class);
                    x6=dataSnapshot.child("bloodType").getValue(String.class);
                    String x7=dataSnapshot.child("weight").getValue(String.class);
                    birthday.setText(x3);
                    phone.setText(x4);
                    gender.setText(x5);
                    blood_type.setText(x6);
                     Weight.setText(x7);
                  //  Weight.setText(Integer.toString(dataSnapshot1.child("weight").getValue(int.class)));
                    Toast.makeText(EditProActivity.this, "view"+x1+x2+x6+x7, Toast.LENGTH_SHORT).show();

                }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        //mTitle = (AppCompatTextView) findViewById(R.id.title);
        back =  (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change to settings
                startActivity(new Intent(EditProActivity.this, SettingActivity.class));
            }
        });
        edit =   findViewById(R.id.edit);
        edit.setOnClickListener(this);
        addImg=  (ImageButton) findViewById(R.id.addImg);
        addImg.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                // in onCreate or any event where your want the user to
                // select a file
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                Toast.makeText(this, ""+selectedImagePath, Toast.LENGTH_SHORT).show();

            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }
    private boolean Validations() {

        if (TextUtils.isEmpty(fullname.getText().toString())) {
            Toast.makeText(this, "please add your name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            Toast.makeText(this, "please add your name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(birthday.getText().toString())) {
            Toast.makeText(this, "please select the birthday date", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(blood_type.getText().toString())) {
            Toast.makeText(this, "please select your blood type", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(gender.getText().toString())) {
            Toast.makeText(this, "please select your gender", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(Weight.getText().toString())) {
            Toast.makeText(this, "please add your Weight", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(phone.getText().toString())) {
            Toast.makeText(this, "please add your phone", Toast.LENGTH_SHORT).show();
            return false;
        }else if (phone.getText().toString().length() < 10) {
            Toast.makeText(this, "Phone Number must be 10 number ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!phone.equals("059")){
            if(!phone.equals("056")){
                Toast.makeText(this, "Phone Number begin with 059 or 056 ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

            case R.id.birthday:
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

                        birthday.setText(formattedDate);
                    }
                };
                new DatePickerDialog(this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),

                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                break;
            case R.id.blood_type:
                String[] pickerVals = new String[]{"O+","O-","A+","A-","B+","B-","AB+","AB-"};
                flag = true;
                showMyDialog(pickerVals, 7, "Blood Type Picker");
                break;
            case R.id.gender:
                String[] pickerVal = new String[]{"Male","Female"};
                flag = false;
                showMyDialog(pickerVal, 1, "Gender Picker");
                break;
            case R.id.edit:
                if (Validations()) {
                    DatabaseReference updateData = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
                    updateData.child("userName").setValue(fullname.getText().toString());
                    updateData.child("email").setValue(email.getText().toString());
                    updateData.child("birthday").setValue(birthday.getText().toString());
                    updateData.child("phoneNumber").setValue(phone.getText().toString());
                    updateData.child("gender").setValue(gender.getText().toString());
                    updateData.child("bloodType").setValue(blood_type.getText().toString());
                    updateData.child("weight").setValue(Weight.getText().toString());

                    Toast.makeText(this, "" + fullname.getText().toString() + " updated", Toast.LENGTH_SHORT).show();
                }
                // if (Validations()) {

                    /*User user = new User(fullname.getText().toString(),
                                        email.getText().toString(),
                                        birthday.getText().toString(),
                                        phone.getText().toString(),
                                        Weight.getText().toString(),
                                        blood_type.getText().toString(),
                                        gender.getText().toString());*/

                  /*  Paper.book().write("gender", gender);
                    Paper.book().write("bloodType", blood_type);
                    Paper.book().write("weight", Weight);
                    Paper.book().write("phoneNumber", phone);
                    Paper.book().write("fullname", fullname);
                    Paper.book().write("email", email);
                    Paper.book().write("birthday", birthday);

                    user.setBloodType((String) Paper.book().read("bloodType"));
                    user.setPhoneNumber((String) Paper.book().read("phone"));
                    user.setGender((String) Paper.book().read("gender"));
                    user.setWeight(mWeightNumber.getText().toString());
*/
                   // SaveToDataBase(user);

            //    }

                break;

        }
    }
    private void SaveToDataBase(User user) {

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
                    blood_type.setText(pickerVals[valuePicker1]);
                } else {
                    gender.setText(pickerVals[valuePicker1]);
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

 /*Map<String,Object> map = new HashMap<>();

        map.put("bloodType",user.getBloodType());
        map.put("phoneNumber",user.getPhoneNumber());
        map.put("gender",user.getGender());
        map.put("weight",user.getWeight());
        map.put("userName",user.getFullname());
        map.put("email",user.getEmail());
        map.put("birthday",user.getBirthday());


        ref.child(uid).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Paper.book().write("userId", uid);
                Toast.makeText(getApplicationContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditProActivity.this, TabActivity.class));
                finish();
            }
        });
*//* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST:
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        addImg.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
    }*/
 /*  addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change to settings
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });*/
       /* edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("User").child(uid)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    dataSnapshot1.getRef().child("userName").setValue(fullname.getText().toString());
                                    dataSnapshot1.getRef().child("email").setValue(email.getText().toString());
                                    dataSnapshot1.getRef().child("birthday").setValue(birthday.getText().toString());
                                    dataSnapshot1.getRef().child("phoneNumber").setValue(phone.getText().toString());
                                    dataSnapshot1.getRef().child("gender").setValue(gender.getText().toString());
                                    dataSnapshot1.getRef().child("bloodType").setValue(blood_type.getText().toString());
                                    //  Weight.setText(Integer.toString(dataSnapshot1.child("weight").getValue(int.class)));

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }

                        });
            }
        });*/
