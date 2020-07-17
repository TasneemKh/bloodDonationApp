package com.example.blooddonationapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;


public class HistoryActivity extends Fragment {

    private static final String TAG1 = "HistoryActivity";

    private Context mContext;
    RecyclerView recyclerView0;
    public static final String TAG = HistoryActivity.class.getSimpleName();

    ArrayList<donationHistory> list0;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    donationAdapter donationAdapter,Adapter;
    FirebaseUser user;
    View view;
    public HistoryActivity() {
        // Required empty public constructor
    }

    //i tried this section but it gimes me error
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable      ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_history,container,false);

        return view;

    }

    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        String uid = user.getUid();

        // Initialize Template Model Class
        // Lookup the Recycler view in fragment layout
        recyclerView0 = getView().findViewById(R.id.donation_rv);
        recyclerView0.setHasFixedSize(true);
        // Attach the adapter to the recyclerview to populate items
        //  donationAdapter = new donationAdapter(template,inflater.getContext());//>>>This is the error i'm facig
        // Set layout manager to position the items
        recyclerView0.setLayoutManager(new LinearLayoutManager(getContext()));

        reference = FirebaseDatabase.getInstance().getReference().child("donations").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list0 = new ArrayList<donationHistory>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    System.out.println(dataSnapshot1.getValue());
                    String donationType = dataSnapshot1.child("donationType").getValue(String.class);
                    System.out.println(donationType);

                    String placeOfDonation = dataSnapshot1.child("placeOfDonation").getValue(String.class);

                    String dateOfDonation = dataSnapshot1.child("dateOfDonation").getValue(String.class);
                 // donationHistory p = dataSnapshot1.getValue(donationHistory.class);
                    donationHistory p =new donationHistory(donationType,placeOfDonation,dateOfDonation);

                    list0.add(p);
                }
                donationAdapter = new donationAdapter(getActivity(), list0);
                recyclerView0.setAdapter(donationAdapter);

            }
            /* String name = value.getP_name();
             String desig = value.getP_designation();
             String email = value.getP_email();
             String phone = value.getP_phone();
             String address = value.getC_address();
         }*/
            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }

        });
    }

    }