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

public class donationAdapter extends RecyclerView.Adapter<donationAdapter.donationVh>{
    Context context ;
    List<donationHistory> donationHistory ;
    DateFormat format;
    public  donationAdapter(Context context,List<donationHistory> donationHistory ){
        this.context = context;
        this.donationHistory=donationHistory;
    }
    @NonNull
    @Override
    public donationVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_row ,parent , false );
        return new donationVh(view);    }

    @Override
    public void onBindViewHolder(@NonNull donationVh holder, int position) {
        holder.setData(donationHistory.get(position));

    }

    @Override
    public int getItemCount() {
        return donationHistory.size();
    }



    class donationVh extends RecyclerView.ViewHolder {
        TextView don_id,Donation_type,bloodbank,date;
        public donationVh(@NonNull View itemView) {
            super(itemView);
            //don_id = itemView.findViewById(R.id.std_id);
            Donation_type = itemView.findViewById(R.id.Donation_type);
            bloodbank = itemView.findViewById(R.id.bloodbank);
            date = itemView.findViewById(R.id.date);
        }

        public void setData(final donationHistory donationHistory) {
            // std_id.setText(donationHistory.getId());
            String x=donationHistory.getDonationType()+" "+"Donation";
            Donation_type.setText(x);
            bloodbank.setText(donationHistory.getPlaceOfDonation());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date.setText(dateFormat.format(donationHistory.getDateOfDonation()));
            //date.setText(format.format(new Date(String.valueOf(donationHistory.getDateOfDonation()))));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  Toast.makeText(context, donationHistory.getDonationType(), Toast.LENGTH_SHORT).show();
                   /* Intent intent = new Intent(itemView.getContext() ,stdActivity.class);
                  //  intent.putExtra("id",donationHistory.getId());
                    intent.putExtra("name",donationHistory.getName());
                    intent.putExtra("level",donationHistory.getLevel());
                    intent.putExtra("Avg",donationHistory.getAvg());
                    itemView.getContext().startActivity(intent);*/
                }
            });

        }
    }
}
