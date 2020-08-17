package com.example.blooddonationapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import com.example.blooddonationapp.Activity.PreDonationCheckActivity;
import com.example.blooddonationapp.R;
import com.example.blooddonationapp.SharedPreferencesApp;
import com.example.blooddonationapp.TabActivity;
import com.example.blooddonationapp.changePager;

import io.paperdb.Paper;


public class Fragment1 extends Fragment  implements View.OnClickListener {

    private AppCompatImageButton mClose;
    private ImageButton mFmale;
    private ImageButton mMale;
    changePager pager;
    /**
     * Next
     */
    private Button mNext;
    private String geander = "";

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Paper.init(getActivity().getApplicationContext());
        initView(getView());
        if (SharedPreferencesApp.getInstance(getContext()).getStringData("flag").equals("done")){
            Toast.makeText(getContext(), "you are complete your data before", Toast.LENGTH_SHORT).show();
            getActivity().startActivity(new Intent(getContext(), PreDonationCheckActivity.class));

            getActivity().finish();
        }

    }

    private void initView(View view) {
        mClose = (AppCompatImageButton) view.findViewById(R.id.close);
        mClose.setOnClickListener(this);
        mFmale = (ImageButton) view.findViewById(R.id.fmale);
        mFmale.setOnClickListener(this);
        mMale = (ImageButton) view.findViewById(R.id.male);
        mMale.setOnClickListener(this);
        mNext = (Button) view.findViewById(R.id.next);
        mNext.setOnClickListener(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        pager = (com.example.blooddonationapp.changePager) context;
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
            case R.id.male:
                geander = "male";
                mMale.setBackground(this.getResources().getDrawable(R.drawable.blood_shap_checked));
                mMale.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_group_31110));
                mFmale.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_group_3110));
                mFmale.setBackground(this.getResources().getDrawable(R.drawable.sq1_shap));
                break;
            case R.id.fmale:
                geander = "Female";
                mFmale.setBackground(this.getResources().getDrawable(R.drawable.blood_shap_checked));
                mFmale.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_group_31101));
                mMale.setBackground(this.getResources().getDrawable(R.drawable.sq1_shap));
                mMale.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_group_3111));
                break;
            case R.id.next:
                if (geander.isEmpty())
                    Toast.makeText(getContext(), "please select your gender", Toast.LENGTH_SHORT).show();
                else {
                    Paper.book().write("gender", geander);
                    pager.putNumber(1);
                }
                break;
        }
    }
}