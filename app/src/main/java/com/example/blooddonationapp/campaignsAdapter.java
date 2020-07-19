package com.example.blooddonationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class campaignsAdapter extends RecyclerView.Adapter<campaignsAdapter.CampVh>{
    Context context ;
    List<Campaigns> Campaigns ;
    DateFormat format;
    public  campaignsAdapter(Context context,List<Campaigns> Campaigns ){
        this.context = context;
        this.Campaigns=Campaigns;
    }
    @NonNull
    @Override
    public CampVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bank_blood_and_compaigns_row ,parent , false );
        return new CampVh(view);    }

    @Override
    public void onBindViewHolder(@NonNull CampVh holder, int position) {
        holder.setData(Campaigns.get(position));

    }

    @Override
    public int getItemCount() {
        return Campaigns.size();
    }



    class CampVh extends RecyclerView.ViewHolder {
        TextView don_id,name,location,numberOfUnits;
        public CampVh(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            location = itemView.findViewById(R.id.location);
            numberOfUnits = itemView.findViewById(R.id.numberOfUnits);
        }

        public void setData(final Campaigns Campaigns) {
            name.setText(Campaigns.getHospital_name());
            numberOfUnits.setText(Campaigns.getNo_units_needed());
            numberOfUnits.setText(Campaigns.getNo_units_needed());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, Campaigns.getHospital_name(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
