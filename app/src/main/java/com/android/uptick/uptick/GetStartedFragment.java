package com.android.uptick.uptick;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GetStartedFragment extends Fragment {

    public GetStartedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get_started, container, false);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        TextView tvTitle = (TextView) view.findViewById(R.id.title_tv);
        TextView tvDesc = (TextView) view.findViewById(R.id.desc_tv);
        TextView tvContinue = (TextView) view.findViewById(R.id.continue_tv);

        tvDesc.setText("You are 3 steps away from your first trade through UpTick!");

        tvTitle.setTypeface(tf);
        tvContinue.setTypeface(tf);
        tvDesc.setTypeface(tf);

        tvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (GetStartedActivity.getViewPager() != null) {
                GetStartedActivity.getViewPager().setCurrentItem(1, true);
                YourDetailsFragment fragment = (YourDetailsFragment)
                        ((GetStartedPagerAdapter) GetStartedActivity.getViewPager().getAdapter()).getRegisteredFragment(1);
                fragment.setFocus();
            }
            }
        });
        return view;
    }
}
