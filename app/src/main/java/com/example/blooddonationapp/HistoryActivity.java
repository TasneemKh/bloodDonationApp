package com.example.blooddonationapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

public class HistoryActivity extends Fragment {
    RecyclerView recyclerView0;
    ArrayList<donationHistory> list0;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    donationAdapter donationAdapter,Adapter;
    FirebaseUser user;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view=inflater.inflate(R.layout.activity_history, container, false);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        recyclerView0 =getActivity().findViewById(R.id.donation_rv);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView0.setLayoutManager(horizontalLayoutManagaer);
        String uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("donations").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list0 = new ArrayList<donationHistory>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    donationHistory p = dataSnapshot1.getValue(donationHistory.class);
                    list0.add(p);
                }
                donationAdapter = new donationAdapter(getActivity().getApplicationContext(), list0);
                recyclerView0.setAdapter(donationAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}