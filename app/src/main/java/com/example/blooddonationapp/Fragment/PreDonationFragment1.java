package com.example.blooddonationapp.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.blooddonationapp.Activity.MainActivityF;
import com.example.blooddonationapp.R;
import com.example.blooddonationapp.TabActivity;

import io.paperdb.Paper;

public class PreDonationFragment1 extends Fragment implements View.OnClickListener {


    NavController navController;
    private ImageButton mTrues;
    private ImageButton mFalses;

    private AppCompatImageView mBack;
    private Toolbar mToolbar;

    public PreDonationFragment1() {
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
        return inflater.inflate(R.layout.fragment_pre_donation1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = Navigation.findNavController(getView());
        initView(getView());
        if (Paper.book().read("gender").equals("male")) {
            navController.navigate(R.id.action_preDonationFragment1_to_preDonationFragment1Yes);
        }
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
                Intent intent = new Intent(getContext(), TabActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.trues:
                final Dialog dialog = new Dialog(getContext(), R.style.mydialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_priod);
                AppCompatImageButton close = dialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getActivity().getApplicationContext(),TabActivity.class));
                    }
                });
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                break;
            case R.id.falses:
                navController.navigate(R.id.action_preDonationFragment1_to_preDonationFragment1Yes);
                break;
        }
    }
}