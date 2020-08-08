package com.example.blooddonationapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.blooddonationapp.R;

public class AfterDonationFragment1 extends Fragment implements View.OnClickListener {


    private AppCompatImageView mBack;
    private AppCompatImageView mClose;
    private Toolbar mToolbar;

    public AfterDonationFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_after_donation1, container, false);
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
                Navigation.findNavController(getView()).popBackStack(R.id.preDonationFragment1, false);
                break;
            case R.id.close:

                break;
        }
    }
}