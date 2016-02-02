package com.android.uptick.uptick;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TourPageFragment extends Fragment {

    public TourPageFragment() {

    }

    public static TourPageFragment newInstance(int position) {
        TourPageFragment tourFragment = new TourPageFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        tourFragment.setArguments(bundle);

        return tourFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tour_page, container, false);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        TextView tvPageTitle = (TextView) view.findViewById(R.id.page_title_tv);
        TextView tvPageDesc = (TextView) view.findViewById(R.id.page_description_tv);
        TextView tvLogin = (TextView) view.findViewById(R.id.login_tv);
        TextView tvGetStarted = (TextView) view.findViewById(R.id.get_started_tv);

        tvPageDesc.setText("I believe it a serious matter as the blue cheese dressing is" +
                " made by a purple penguin smiling at the green clouds even though they" +
                " are redder than green. It is of utmost importance.");

        tvPageTitle.setTypeface(tf);
        tvPageDesc.setTypeface(tf);
        tvLogin.setTypeface(tf);
        tvGetStarted.setTypeface(tf);

        int position = getArguments().getInt("position");

        switch (position) {
            case 1:
                tvPageTitle.setText("Tour Page 1");
                TextView tvDot2 = (TextView) view.findViewById(R.id.page_2_dot_tv);
                tvDot2.setTypeface(null, Typeface.BOLD);
                tvDot2.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 2:
                tvPageTitle.setText("Tour Page 2");
                TextView tvDot3 = (TextView) view.findViewById(R.id.page_3_dot_tv);
                tvDot3.setTypeface(null, Typeface.BOLD);
                tvDot3.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 3:
                tvPageTitle.setText("Tour Page 3");
                TextView tvDot4 = (TextView) view.findViewById(R.id.page_4_dot_tv);
                tvDot4.setTypeface(null, Typeface.BOLD);
                tvDot4.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 4:
                tvPageTitle.setText("Tour Page 4");
                TextView tvDot5 = (TextView) view.findViewById(R.id.page_5_dot_tv);
                tvDot5.setTypeface(null, Typeface.BOLD);
                tvDot5.setTextColor(Color.parseColor("#FFFFFF"));
                break;
        }

        tvGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), GetStartedActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
