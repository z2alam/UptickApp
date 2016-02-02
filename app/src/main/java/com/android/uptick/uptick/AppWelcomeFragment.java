package com.android.uptick.uptick;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AppWelcomeFragment extends Fragment {

    public AppWelcomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        TextView tvGetStarted = (TextView) view.findViewById(R.id.get_started_tv);
        TextView tvTitle = (TextView) view.findViewById(R.id.title_tv);
        TextView tvTakeTour = (TextView) view.findViewById(R.id.take_tour_tv);
        TextView tvLogin = (TextView) view.findViewById(R.id.login_tv);
        ImageView imTakeTour = (ImageView) view.findViewById(R.id.take_tour_im);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        tvGetStarted.setTypeface(tf, Typeface.NORMAL);
        tvTitle.setTypeface(tf, Typeface.BOLD);
        tvTakeTour.setTypeface(tf, Typeface.NORMAL);
        tvLogin.setTypeface(tf, Typeface.NORMAL);

        Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        imTakeTour.startAnimation(shake);
        imTakeTour.getAnimation().setRepeatCount(1000);

        LinearLayout layoutTakeTour = (LinearLayout) view.findViewById(R.id.take_tour_layout);
        layoutTakeTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (MainActivity.getViewPager() != null) {
                MainActivity.getViewPager().setCurrentItem(1, true);
            }
            }
        });

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
