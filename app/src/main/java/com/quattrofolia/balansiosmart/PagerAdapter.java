package com.quattrofolia.balansiosmart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by eemeliheinonen on 27/10/2016.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    public PagerAdapter(FragmentManager fm) { super(fm); }

    public int getCount() {
        return PAGE_COUNT;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return GoalTypeFragment.newInstance();
            case 1:
                return GoalIntensityFragment.newInstance();
            case 2:
                return GoalRangeFragment.newInstance();
            case 3:
                return GoalNotificationFragment.newInstance();
        }
        return null;
    }
}