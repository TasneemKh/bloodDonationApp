package com.example.blooddonationapp.Fragment;

import android.content.Context;
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

import com.example.blooddonationapp.R;
import com.example.blooddonationapp.TabActivity;
import com.example.blooddonationapp.changePager;
import com.google.android.material.textfield.TextInputEditText;

import io.paperdb.Paper;


public class Fragment4 extends Fragment implements View.OnClickListener {

    private AppCompatImageButton mClose;
    /**
     * enter your phone number
     */
    private TextInputEditText mIdNumber;
    /**
     * Next
     */
    private Button mNext;
    changePager pager;

    public Fragment4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_4, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        pager = (com.example.blooddonationapp.changePager) context;
    }

    private void initView(View view) {
        mClose = (AppCompatImageButton) view.findViewById(R.id.close);
        mClose.setOnClickListener(this);
        mIdNumber = (TextInputEditText) view.findViewById(R.id.identity_number);
        mNext = (Button) view.findViewById(R.id.next);
        mNext.setOnClickListener(this);
    }

    private boolean Validations() {
        if (TextUtils.isEmpty(mIdNumber.getText().toString())){
            Toast.makeText(getContext(), "please enter your phone number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (mIdNumber.getText().toString().length() < 9) {
            Toast.makeText(getContext(), "Phone Number must be 9 number ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
            case R.id.next:
                if (Validations()){
                    Paper.book().write("id", mIdNumber.getText().toString());
                    pager.putNumber(1);
                }
                break;
        }
    }
}