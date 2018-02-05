package com.example.vsing.splash_screen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by SHUBHAM on 27-01-2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {


    int mNoOfTabs;
    public PagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override

    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Location l1 = new Location();
                return l1;
            case 1:
                Chat l2 = new Chat();
                return l2;
            case 2:
                News l3 = new News();
                return l3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
