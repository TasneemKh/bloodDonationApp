package com.example.blooddonationapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blooddonationapp.Activity.MainActivityF;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.content.Context.MODE_PRIVATE;


public class home extends Fragment {
        ImageButton req,settings;
        Button b,b2 , edit,timer;
        private FirebaseAuth mAuth;
        FirebaseUser user;
    private EditText mEditTextInput;
    private TextView mTextViewCountDown,reminderPeriod,drugDurations,day,hour,min,sec,textView35;
    private Button mButtonStartPause;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;
    String uid;
        TextView name;/*,reminderPeriod,drugDurations;*/
    ConstraintLayout bookLay,downCount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        uiInitlize();
       /* bookLay.setVisibility(View.VISIBLE);
        downCount.setVisibility(View.INVISIBLE);*/
        uid =FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().child("User").child(uid).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                        String userName =snapshot.child("userName").getValue(String.class);
                        name.setText(userName);
                        int drugDurations0= snapshot.child("drugDurations").getValue(Integer.class);
                        drugDurations.setText(Integer.toString(drugDurations0));
                        int reminderPeriod0=snapshot.child("reminderPeriod").getValue(Integer.class);
                        reminderPeriod.setText(Integer.toString(reminderPeriod0));
                        int tot= (drugDurations0+reminderPeriod0)*1440;
                      mEditTextInput.setText(Integer.toString(tot));
                       // mEditTextInput.setText(Integer.toString(1));
                        mButtonStartPause.performClick();

                    }
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "crash", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEditTextInput.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(getActivity().getApplicationContext(), "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    bookLay.setVisibility(View.VISIBLE);
                    downCount.setVisibility(View.INVISIBLE);
                    return;
                }
                setTime(millisInput);
                mEditTextInput.setText("");
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });


        req.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), Add_Request_Activity.class);
                startActivity(i);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), SettingActivity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), MainActivityF.class);
                startActivity(i);
            }
        });
        timer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), timer.class);
                startActivity(i);
            }
        });
        textView35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.biobridgeglobal.org/news/why-wait-between-blood-donations";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

    }
    public void uiInitlize(){
        req=getView().findViewById(R.id.imageButton);
        settings=getView().findViewById(R.id.settings);
        b2=getView().findViewById(R.id.button2);
        timer=getView().findViewById(R.id.timer);
        name=getView().findViewById(R.id.name);
        bookLay=getView().findViewById(R.id.bookLay);
        downCount=getView().findViewById(R.id.downCount);
        reminderPeriod=getView().findViewById(R.id.reminderPeriod);
        drugDurations=getView().findViewById(R.id.drugDurations);
      //  mTextViewCountDown = getView().findViewById(R.id.text_view_countdown);
        day = getView().findViewById(R.id.day);
        hour = getView().findViewById(R.id.hour);
        min = getView().findViewById(R.id.min);
        sec = getView().findViewById(R.id.sec);
        drugDurations=(TextView)getView().findViewById(R.id.drugDurations);
        reminderPeriod=(TextView)getView().findViewById(R.id.reminderPeriod);
        mEditTextInput = getView().findViewById(R.id.edit_text_input);
        mButtonStartPause = getView().findViewById(R.id.button_start_pause);
        textView35=getView().findViewById(R.id.textView35);
    }
    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }
    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateWatchInterface();
            }
        }.start();
        mTimerRunning = true;
        updateWatchInterface();
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateWatchInterface();
    }
    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }
    private void updateCountDownText() {

        int days = (int) (mTimeLeftInMillis / 1000) / 3600/24;
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600%24;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted;
        if (hours > 0||days>0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d:%02d", days,hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d:%02d:%02d", days,hours,minutes, seconds);
        }

        //mTextViewCountDown.setText(timeLeftFormatted);
        String x = String.format(Locale.getDefault(), "%02d", days);
        String x2 = String.format(Locale.getDefault(), "%02d", hours);
        String x3 = String.format(Locale.getDefault(), "%02d", minutes);
        String x4 = String.format(Locale.getDefault(), "%02d", seconds);


        day.setText(x);
        hour.setText(x2);
        min.setText(x3);
        sec.setText(x4);
    }
    private void updateWatchInterface() {
        if (mTimerRunning) {
            mEditTextInput.setVisibility(View.INVISIBLE);
            // mButtonReset.setVisibility(View.INVISIBLE);
            //mButtonStartPause.setText("Pause");
        } else {
            mEditTextInput.setVisibility(View.VISIBLE);
            mButtonStartPause.setText("Start");
            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);
            } else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }
            if (mTimeLeftInMillis < mStartTimeInMillis) {
                //  mButtonReset.setVisibility(View.VISIBLE);
                //resetTimer();
                Toast.makeText(getActivity().getApplicationContext(),"I am here",Toast.LENGTH_SHORT).show();
                DatabaseReference updateData = FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                updateData.child("reminderPeriod").setValue(0);
                updateData.child("drugDurations").setValue(0);
                bookLay.setVisibility(View.VISIBLE);
                downCount.setVisibility(View.INVISIBLE);

            } else {
                // mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);
        editor.apply();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", MODE_PRIVATE);
        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
        mTimerRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        updateWatchInterface();
        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
                updateWatchInterface();
            } else {
                startTimer();
            }
        }
    }
}

