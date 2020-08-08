package com.example.blooddonationapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.blooddonationapp.R;
import com.example.blooddonationapp.TabActivity;
import com.example.blooddonationapp.ToggleButtonGroupTableLayout;
import com.example.blooddonationapp.changePager;

import io.paperdb.Paper;

public class Fragment3 extends Fragment  implements View.OnClickListener {

    private AppCompatImageButton mClose;
    /**
     * O+
     */
    private RadioButton mOP;
    /**
     * O-
     */
    private RadioButton mOM;
    /**
     * A+
     */
    private RadioButton mAP;
    /**
     * A-
     */
    private RadioButton mAM;
    /**
     * B+
     */
    private RadioButton mBP;
    /**
     * B-
     */
    private RadioButton mBM;
    /**
     * AB+
     */
    private RadioButton mAbP;
    /**
     * AB-
     */
    private RadioButton mAbM;
    /**
     * Next
     */
    private Button mNext;
    private String bloodType = "";
    changePager pager;
    private RadioButton radioButton;

    private ToggleButtonGroupTableLayout mRadGroup1;

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_3, container, false);
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
        mOP = (RadioButton) view.findViewById(R.id.o_p);
        mOM = (RadioButton) view.findViewById(R.id.o_m);
        mAP = (RadioButton) view.findViewById(R.id.a_p);
        mAM = (RadioButton) view.findViewById(R.id.a_m);
        mBP = (RadioButton) view.findViewById(R.id.b_p);
        mBM = (RadioButton) view.findViewById(R.id.b_m);
        mAbP = (RadioButton) view.findViewById(R.id.ab_p);
        mAbM = (RadioButton) view.findViewById(R.id.ab_m);
        mNext = (Button) view.findViewById(R.id.next);
        mNext.setOnClickListener(this);
        mRadGroup1 = (ToggleButtonGroupTableLayout) view.findViewById(R.id.radGroup1);

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
                if (mRadGroup1.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getContext(), "please select your blood type", Toast.LENGTH_SHORT).show();
                }
                else {
                    radioButton = getView().findViewById(mRadGroup1.getCheckedRadioButtonId());
                    Paper.book().write("bloodType", radioButton.getText().toString());
                    pager.putNumber(1);
                }
                break;
        }
    }
}