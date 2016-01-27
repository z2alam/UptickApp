package com.android.uptick.uptick;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AppWelcomeFragment extends Fragment {

    public AppWelcomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_fragment, container, false);

        TextView tvGetStarted = (TextView) view.findViewById(R.id.get_started_tv);
        TextView tvTitle = (TextView) view.findViewById(R.id.title_tv);
        TextView tvTakeTour = (TextView) view.findViewById(R.id.take_tour_tv);
        TextView tvLogin = (TextView) view.findViewById(R.id.login_tv);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        tvGetStarted.setTypeface(tf, Typeface.NORMAL);
        tvTitle.setTypeface(tf, Typeface.BOLD);
        tvTakeTour.setTypeface(tf, Typeface.NORMAL);
        tvLogin.setTypeface(tf, Typeface.NORMAL);

        return view;
    }

}
