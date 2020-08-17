package com.example.blooddonationapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.blooddonationapp.Activity.PreDonationCheckActivity;
import com.example.blooddonationapp.Model.User;
import com.example.blooddonationapp.R;
import com.example.blooddonationapp.SharedPreferencesApp;
import com.example.blooddonationapp.TabActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;

public class Fragment5 extends Fragment implements View.OnClickListener {
    String uid;
    FirebaseUser user;

    private AppCompatImageButton mClose;
    /**
     * enter your phone number
     */
    private TextInputEditText mWeightNumber;
    /**
     * Next
     */
    private Button mNext;

    DatabaseReference ref;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;

    public Fragment5() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_5, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("User");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
         uid = user.getUid();
    }

    private void initView(View view) {
        mClose = (AppCompatImageButton) view.findViewById(R.id.close);
        mClose.setOnClickListener(this);
        mWeightNumber = (TextInputEditText) view.findViewById(R.id.weight_number);
        mNext = (Button) view.findViewById(R.id.save);
        mNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.close:
                Intent intent = new Intent(getActivity().getApplicationContext() , TabActivity.class);
                startActivity(intent);
                break;
            case R.id.save:
                if (TextUtils.isEmpty(mWeightNumber.getText().toString()))
                    Toast.makeText(getContext(), "please enter your Weight number", Toast.LENGTH_SHORT).show();
                else {
                    User user = new User();
                    user.setBloodType((String) Paper.book().read("bloodType"));
                    user.setPhoneNumber((String) Paper.book().read("phone"));
                    user.setIdentityNumber((String) Paper.book().read("id"));
                    user.setGender((String) Paper.book().read("gender"));
                    user.setWeight(mWeightNumber.getText().toString());

                    SaveToDataBase(user);
                }
                break;
        }
    }

    private void SaveToDataBase(User user) {
        Map<String,Object> map = new HashMap<>();

        map.put("bloodType",(String) Paper.book().read("bloodType"));
        map.put("phoneNumber",(String) Paper.book().read("phone"));
        map.put("identityNumber",(String) Paper.book().read("id"));
        map.put("gender",(String) Paper.book().read("gender"));
        map.put("weight",mWeightNumber.getText().toString());

        ref.child(uid).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Paper.book().write("userId", uid);
                Toast.makeText(getContext(), "Add successfully", Toast.LENGTH_SHORT).show();
                SharedPreferencesApp.getInstance(getContext()).saveData("flag","done");
                getActivity().startActivity(new Intent(getContext(), PreDonationCheckActivity.class));
                getActivity().finish();
            }
        });

//        ref.push().setValue(user)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(getContext(), "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
//                        getActivity().startActivity(new Intent(getContext(), PreDonationCheckActivity.class));
//                        getActivity().finish();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
    }
}