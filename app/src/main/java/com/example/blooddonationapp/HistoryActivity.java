package com.example.blooddonationapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.view.View.GONE;


public class HistoryActivity extends Fragment {

    private static final String TAG1 = "HistoryActivity";

    private Context mContext;
    RecyclerView recyclerView0;
    public static final String TAG = HistoryActivity.class.getSimpleName();
    Date date;
    ArrayList<donationHistory> list0,list1,list2;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    donationAdapter donationAdapter,Adapter;
    FirebaseUser user;
    ImageButton filter;
    View view;
    String uid;
    ProgressBar pgsBar;
    private LinearLayoutManager mLayoutManager;

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
        pgsBar = (ProgressBar)getView().findViewById(R.id.pBar);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
         uid = user.getUid();
        recyclerView0 = getView().findViewById(R.id.donation_rv);
        recyclerView0.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView0.setLayoutManager(mLayoutManager);
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
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                         date = format.parse(dateOfDonation);
                        System.out.println(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                 // donationHistory p = dataSnapshot1.getValue(donationHistory.class);
                   // donationHistory p =new donationHistory(donationType,placeOfDonation,dateOfDonation);
                    donationHistory p =new donationHistory(donationType,placeOfDonation,date);

                    list0.add(p);
                }
                donationAdapter = new donationAdapter(getActivity(), list0);
                int i=donationAdapter.getItemCount();
                System.out.println(i);
                pgsBar.setVisibility(GONE);
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
        filter=getView().findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_single_choice_array);
                int itemSelected = 0;
                AlertDialog alertDialog= new AlertDialog.Builder(getContext())
                        .setTitle("Filter your history donation")
                        .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                            if (selectedIndex ==0){
                                mLayoutManager.setReverseLayout(true);
                                mLayoutManager.setStackFromEnd(true);
                                recyclerView0.setLayoutManager(mLayoutManager);
                                donationAdapter = new donationAdapter(getActivity(), list0);
                                recyclerView0.setAdapter(donationAdapter);
                            }
                            else if(selectedIndex ==1){
                                mLayoutManager.setReverseLayout(false);
                                mLayoutManager.setStackFromEnd(false);
                                recyclerView0.setLayoutManager(mLayoutManager);
                                donationAdapter = new donationAdapter(getActivity(), list0);
                                recyclerView0.setAdapter(donationAdapter);
                            }
                            else if(selectedIndex ==2){
                                mLayoutManager.setReverseLayout(true);
                                mLayoutManager.setStackFromEnd(true);
                                recyclerView0.setLayoutManager(mLayoutManager);
                                reference = FirebaseDatabase.getInstance().getReference().child("donations").child(uid);
                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        list1= new ArrayList<donationHistory>();
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                            String donationType = dataSnapshot1.child("donationType").getValue(String.class);
                                            if(donationType.equals("Blood Donation")){
                                            String placeOfDonation = dataSnapshot1.child("placeOfDonation").getValue(String.class);
                                            String dateOfDonation = dataSnapshot1.child("dateOfDonation").getValue(String.class);
                                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                            try {
                                                date = format.parse(dateOfDonation);
                                                System.out.println(date);
                                            } catch (ParseException e) { e.printStackTrace(); }
                                            donationHistory p =new donationHistory(donationType,placeOfDonation,date);
                                            list1.add(p);
                                            }

                                        }
                                        donationAdapter = new donationAdapter(getActivity(), list1);
                                        recyclerView0.setAdapter(donationAdapter);

                                    }
                                    @Override
                                    public void onCancelled (@NonNull DatabaseError error){

                                    }
                                });
                            }
                            else if(selectedIndex ==3){
                                mLayoutManager.setReverseLayout(true);
                                mLayoutManager.setStackFromEnd(true);
                                recyclerView0.setLayoutManager(mLayoutManager);
                                reference = FirebaseDatabase.getInstance().getReference().child("donations").child(uid);
                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        list2 = new ArrayList<donationHistory>();
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                            String donationType = dataSnapshot1.child("donationType").getValue(String.class);
                                            if(donationType.equals("Platelets Donation")){
                                                String placeOfDonation = dataSnapshot1.child("placeOfDonation").getValue(String.class);
                                                String dateOfDonation = dataSnapshot1.child("dateOfDonation").getValue(String.class);
                                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                                try {
                                                    date = format.parse(dateOfDonation);
                                                    System.out.println(date);
                                                } catch (ParseException e) { e.printStackTrace(); }
                                                donationHistory p =new donationHistory(donationType,placeOfDonation,date);

                                                list2.add(p);
                                            }
                                        }
                                        donationAdapter = new donationAdapter(getActivity(), list2);
                                        recyclerView0.setAdapter(donationAdapter);

                                    }
                                    @Override
                                    public void onCancelled (@NonNull DatabaseError error){

                                    }
                                });
                            }
                                dialogInterface.dismiss();
                            }
                        })
                       /* .setPositiveButton("Ok", null)
                        .setNegativeButton("Cancel", null)*/
                        .show();

            }
        });

    }

    }