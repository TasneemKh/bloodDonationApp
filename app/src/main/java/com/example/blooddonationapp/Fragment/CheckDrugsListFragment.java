package com.example.blooddonationapp.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.blooddonationapp.Activity.DonationTypeActivity;
import com.example.blooddonationapp.Activity.PreDonationCheckActivity;
import com.example.blooddonationapp.Adapter.DrugHolder;
import com.example.blooddonationapp.Model.Drug;
import com.example.blooddonationapp.Model.User;
import com.example.blooddonationapp.R;
import com.example.blooddonationapp.TabActivity;
import com.example.blooddonationapp.changePager;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;

public class CheckDrugsListFragment extends Fragment implements View.OnClickListener {

    String uid;
    FirebaseUser user;
    private FirebaseAuth mAuth;

    private FirebaseRecyclerAdapter<Drug, DrugHolder> adapter;
    private RecyclerView mResc;
    /**
     * Save
     */
    private Button mSave;

    int drugDurations = 0;


    DatabaseReference ref;
    FirebaseDatabase database;

    public CheckDrugsListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_drugs_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("User");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Drugs")
                .limitToLast(50);

        FirebaseRecyclerOptions<Drug> options =
                new FirebaseRecyclerOptions.Builder<Drug>()
                        .setQuery(query, Drug.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Drug, DrugHolder>(options) {
            @Override
            public DrugHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_layout, parent, false);

                return new DrugHolder(view);
            }

            @Override
            protected void onBindViewHolder(DrugHolder holder, int position, final Drug model) {
                holder.checkBox.setText(model.getDrugsName());

                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            drugDurations += model.getDrugsTime();
                        } else {
                            drugDurations -= model.getDrugsTime();
                        }
                    }
                });

            }
        };

        mResc.setAdapter(adapter);
    }

    private void initView(View view) {
        mResc = (RecyclerView) view.findViewById(R.id.resc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mResc.setLayoutManager(layoutManager);
        mSave = (Button) view.findViewById(R.id.save);
        mSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.save:
                SaveToDataBase();
                final Dialog dialog = new Dialog(getContext(), R.style.mydialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_drugs);
                AppCompatImageButton close = dialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        startActivity(new Intent(getActivity(), TabActivity.class));
                    }
                });
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                break;
            case R.id.back:

                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void SaveToDataBase() {
        Map<String, Object> map = new HashMap<>();

        map.put("drugDurations", drugDurations);

        ref.child(uid).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Paper.book().write("userId", uid);
                Toast.makeText(getContext(), "Add successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

}