package com.example.blooddonationapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class datePicker /*extends BottomSheetDialogFragment */extends AppCompatActivity{
    NumberPicker noOfUnits;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);

        noOfUnits=(NumberPicker)findViewById(R.id.unitsNumber);
        noOfUnits.setMaxValue(30);
        noOfUnits.setMinValue(1);
        noOfUnits.setWrapSelectorWheel(true);
        noOfUnits.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d", value);
            }
        });
        noOfUnits.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker1 = noOfUnits.getValue();
                System.out.println("picker value"+ i1+" " +valuePicker1);
            }
        });
    }

    }
    /*public class datePicker  extends DialogFragment {
    NumberPicker noOfUnits;
    private NumberPicker.OnValueChangeListener valueChangeListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final NumberPicker numberPicker = new NumberPicker(getActivity());

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(60);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Value");
        builder.setMessage("Choose a number :");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,
                        numberPicker.getValue(), numberPicker.getValue());
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,
                        numberPicker.getValue(), numberPicker.getValue());
            }
        });

        builder.setView(numberPicker);
        return builder.create();
    }

    public NumberPicker.OnValueChangeListener getValueChangeListener() {
        return valueChangeListener;
    }

    public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }
}*/

/*

public class datePicker  extends AppCompatActivity{
        NumberPicker noOfUnits;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);

        noOfUnits=(NumberPicker)findViewById(R.id.unitsNumber);
        noOfUnits.setMaxValue(30);
        noOfUnits.setMinValue(1);
        noOfUnits.setWrapSelectorWheel(true);
        noOfUnits.setFormatter(new NumberPicker.Formatter() {
@Override
public String format(int value) {
        return String.format("%02d", value);
        }
        });
        noOfUnits.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
@Override
public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        int valuePicker1 = noOfUnits.getValue();
        System.out.println("picker value"+ i1+" " +valuePicker1);
        }
        });
        }
   /* @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.date_picker, container, false);

        return v;
    }
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        noOfUnits=(NumberPicker)getView().findViewById(R.id.unitsNumber);
        noOfUnits.setMaxValue(100);
        noOfUnits.setMinValue(5);
        noOfUnits.setWrapSelectorWheel(false);
        noOfUnits.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker1 = noOfUnits.getValue();
                System.out.println("picker value"+ i1+" " +valuePicker1);
            }
        });*/
        /*NumberPicker number_picker = (NumberPicker)getView().findViewById(R.id.unitsNumber);
        String[] values = new String[51];
        for (int i = 0; i < values.length; i++) {
            String number = Integer.toString(i * 10);
            values[i] = number;
        }
        number_picker.setMinValue(0);
        number_picker.setMaxValue(50);
        number_picker.setWrapSelectorWheel(true);
        number_picker.setDisplayedValues(values);
        number_picker.setValue(20);
        number_picker.setEnabled(true);
        number_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                System.out.println(newVal);
            }
        });*/

        //  noOfUnits.setOnValueChangedListener(this);

       /* noOfUnits.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d",value);
            }
        });
        String[] values = new String[51];
        for (int i = 0; i < values.length; i++) {
            String number = Integer.toString(i * 10);
            values[i] = number;
        }
        noOfUnits.setMinValue(0);
        noOfUnits.setMaxValue(50);
        noOfUnits.setWrapSelectorWheel(false);
        noOfUnits.setDisplayedValues(values);*/
    /*    }

        */