package com.example.blooddonationapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Blood_type_Activity extends BottomSheetDialogFragment {
    RadioButton radioOpos;
    @Override
    public View onCreateView( @Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //super. onCreateView(inflater,container,savedInstanceState);
        // Inflate the layout for this fragment
        //setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
public interface shareDataInterface{

}
  /*  protected void onCreate(Bundle savedInstanceState) {
        onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_type);
        radioOpos=findViewById(R.id.radioOpos);

    }*/
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str="";
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioOpos:
                if(checked)
                    radioOpos.setTextColor(getResources().getColor(R.color.colorWhite));
               // radioOpos.setBackgroundTintList();
               // radioOpos.setBackground(getResources().getColor(@drawable/checked_btn));


                // radioOpos.setBac(contextInstance.getResources().getColorStateList(R.color.);
                break;
            case R.id.radioOneg:
                if(checked)
                    str = "O neg";
                break;
            case R.id.radioApos:
                if(checked)
                    str = "A pos";
                break;
            case R.id.radioAneg:
                if(checked)
                    str = "A neg";
                break;
            case R.id.radioBpos:
                if(checked)
                    str = "B pos";
                break;
            case R.id.radioBneg:
                if(checked)
                    str = "B neg";
                break;
            case R.id.radioABpos:
                if(checked)
                    str = "AB pos";
                break;
            case R.id.radioABneg:
                if(checked)
                    str = "ABneg";
                break;
        }

    }
}
