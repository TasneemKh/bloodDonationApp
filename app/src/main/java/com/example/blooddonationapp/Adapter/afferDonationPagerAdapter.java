package com.example.blooddonationapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.blooddonationapp.Fragment.AfterDonationFragment1;
import com.example.blooddonationapp.Fragment.AfterDonationFragment2;
import com.example.blooddonationapp.Fragment.AfterDonationFragment3;


public class afferDonationPagerAdapter  extends FragmentStatePagerAdapter {
    public afferDonationPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new AfterDonationFragment1();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new AfterDonationFragment2();
            case 2: // Fragment # 1 - This will show SecondFragment
                return new AfterDonationFragment3();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
