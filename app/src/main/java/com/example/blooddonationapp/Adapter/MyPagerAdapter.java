package com.example.blooddonationapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.blooddonationapp.Fragment.Fragment1;
import com.example.blooddonationapp.Fragment.Fragment2;
import com.example.blooddonationapp.Fragment.Fragment3;
import com.example.blooddonationapp.Fragment.Fragment4;
import com.example.blooddonationapp.Fragment.Fragment5;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new Fragment1();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new Fragment2();
            case 2: // Fragment # 1 - This will show SecondFragment
                return new Fragment3();
            case 3: // Fragment # 1 - This will show SecondFragment
                return new Fragment4();
            case 4: // Fragment # 1 - This will show SecondFragment
                return new Fragment5();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
