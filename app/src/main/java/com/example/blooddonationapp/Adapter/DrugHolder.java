package com.example.blooddonationapp.Adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonationapp.R;

public class DrugHolder extends RecyclerView.ViewHolder {
    public AppCompatCheckBox checkBox;
    public DrugHolder(@NonNull View itemView) {
        super(itemView);
        checkBox = itemView.findViewById(R.id.drug_name);
    }
}
