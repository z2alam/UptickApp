package com.android.uptick.uptick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class GetStartedActivity extends AppCompatActivity {

    private static NonSwipeableViewPager mPager;
    private static GetStartedActivity instance;
    GetStartedPagerAdapter mAdapter;

    public static ViewPager getViewPager() {
        return mPager;
    }

    public static GetStartedActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        mPager = (NonSwipeableViewPager) findViewById(R.id.get_started_pager);
        mAdapter = new GetStartedPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        int currPosition = mPager.getCurrentItem();

        if (currPosition == 0)
            super.onBackPressed();
        else
            mPager.setCurrentItem(currPosition-1, true);
    }
}
