package com.example.blooddonationapp.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.blooddonationapp.R;

public class PreDonationFragment1Yes extends Fragment  implements View.OnClickListener   {

    NavController navController;
    private ImageButton mTrues;
    private ImageButton mFalses;

    private AppCompatImageView mBack;
    private Toolbar mToolbar;

    public PreDonationFragment1Yes() {
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
        return inflater.inflate(R.layout.fragment_pre_donation_fragment1_yes, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = Navigation.findNavController(getView());
        initView(getView());
    }

    private void initView(View view) {
        mTrues = (ImageButton) view.findViewById(R.id.trues);
        mFalses = (ImageButton) view.findViewById(R.id.falses);
        mTrues.setOnClickListener(this);
        mFalses.setOnClickListener(this);
        mBack = (AppCompatImageView) view.findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.back:
                Navigation.findNavController(getView()).popBackStack(R.id.preDonationFragment1, false);
                break;
            case R.id.trues:
                navController.navigate(R.id.action_preDonationFragment1Yes_to_preDonationFragment1No);
                break;
            case R.id.falses:
                navController.navigate(R.id.action_preDonationFragment1Yes_to_DonationTypeActivity);
                break;
        }
    }
}