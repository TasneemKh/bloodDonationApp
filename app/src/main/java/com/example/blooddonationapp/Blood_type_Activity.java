package com.example.blooddonationapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Blood_type_Activity extends BottomSheetDialogFragment {
    RadioButton radioOpos;
    RadioGroup radioGroup;
    Button ok;
    String str;
    private shareDataInterface mListener;
    @Override
    public View onCreateView( @Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //super. onCreateView(inflater,container,savedInstanceState);
        // Inflate the layout for this fragment
        //setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
        View v= inflater.inflate(R.layout.activity_blood_type, container, false);
        ok=v.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked(str);
                dismiss();
            }
        });
        return v;
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.opositive:
                if (checked)
                    System.out.println("positive");
                str = "O pos";
                //mlistener.isC("O positive");
                // dismiss();
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
public interface shareDataInterface{
        void onButtonClicked (String text);
}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (shareDataInterface) context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() +" must be implemented");
        }
    }


}
