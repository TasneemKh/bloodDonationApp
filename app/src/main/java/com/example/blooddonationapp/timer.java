package com.example.blooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class timer extends AppCompatActivity {
    private EditText mEditTextInput;
    private TextView mTextViewCountDown,reminderPeriod,drugDurations,day,hour,min,sec;
    private Button mButtonStartPause;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;
    String uid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        drugDurations=(TextView)findViewById(R.id.drugDurations);
        reminderPeriod=(TextView)findViewById(R.id.reminderPeriod);
        mEditTextInput = findViewById(R.id.edit_text_input);
        mButtonStartPause = findViewById(R.id.button_start_pause);

        uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().child("User").child(uid).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int drugDurations0= snapshot.child("drugDurations").getValue(Integer.class);
               drugDurations.setText(Integer.toString(drugDurations0));
               Toast.makeText(getApplicationContext(),""+drugDurations0,Toast.LENGTH_SHORT).show();
                int reminderPeriod0=snapshot.child("reminderPeriod").getValue(Integer.class);
                reminderPeriod.setText(Integer.toString(reminderPeriod0));
                Toast.makeText(getApplicationContext(),""+reminderPeriod0,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),""+uid,Toast.LENGTH_SHORT).show();
                int tot= (drugDurations0+reminderPeriod0)*1440;
                mEditTextInput.setText(Integer.toString(tot));
                mButtonStartPause.performClick();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /* /
*/
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        day = findViewById(R.id.day);
        hour = findViewById(R.id.hour);
        min = findViewById(R.id.min);
        sec = findViewById(R.id.sec);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEditTextInput.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(timer.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(timer.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
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

        mTextViewCountDown.setText(timeLeftFormatted);
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
                Toast.makeText(getApplicationContext(),"I am here",Toast.LENGTH_SHORT).show();
                DatabaseReference updateData = FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                updateData.child("reminderPeriod").setValue(0);
                updateData.child("drugDurations").setValue(0);

            } else {
               // mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
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
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
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