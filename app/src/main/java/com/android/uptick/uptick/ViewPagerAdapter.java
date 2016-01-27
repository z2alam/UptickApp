package com.android.uptick.uptick;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position)  {
        if (position == 0)
            return new AppWelcomeFragment();
        else
            return new TourPage1Fragment();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
