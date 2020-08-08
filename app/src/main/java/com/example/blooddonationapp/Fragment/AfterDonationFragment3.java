package com.example.blooddonationapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blooddonationapp.Activity.PreDonationCheckActivity;
import com.example.blooddonationapp.R;

public class AfterDonationFragment3 extends Fragment implements View.OnClickListener {

    private AppCompatImageView mBack;
    private AppCompatImageView mClose;
    private Toolbar mToolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_after_donation3, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
    }

    private void initView(View view) {
        mBack = (AppCompatImageView) view.findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mClose = (AppCompatImageView) view.findViewById(R.id.close);
        mClose.setOnClickListener(this);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                getActivity().startActivity(new Intent(getContext(), PreDonationCheckActivity.class));
                //Navigation.findNavController(getView()).popBackStack(R.id.preDonationFragment1, false);
                break;
            case R.id.close:

                break;
        }
    }
}