package com.android.uptick.uptick;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CompletionFragment extends Fragment {

    public CompletionFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.step3_completion, container, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.toolbar_title);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        tvTitle.setTypeface(tf, Typeface.NORMAL);

        TextView step1Label = (TextView) view.findViewById(R.id.step1_label);
        TextView firstNameLabel = (TextView) view.findViewById(R.id.first_name_label_tv);
        TextView firstNameText = (TextView) view.findViewById(R.id.first_name_text_tv);
        TextView lastNameLabel = (TextView) view.findViewById(R.id.last_name_label_tv);
        TextView lastNameText = (TextView) view.findViewById(R.id.last_name_text_tv);
        TextView emailAddressLabel = (TextView) view.findViewById(R.id.email_address_label_tv);
        TextView emailAddressText = (TextView) view.findViewById(R.id.email_address_text_tv);
        TextView dateLabel = (TextView) view.findViewById(R.id.date_label_tv);
        TextView dateText = (TextView) view.findViewById(R.id.date_text_tv);

        step1Label.setTypeface(tf, Typeface.BOLD);
        firstNameLabel.setTypeface(tf);
        firstNameText.setTypeface(tf);
        lastNameLabel.setTypeface(tf);
        lastNameText.setTypeface(tf);
        emailAddressLabel.setTypeface(tf);
        emailAddressText.setTypeface(tf);
        dateLabel.setTypeface(tf);
        dateText.setTypeface(tf);

        firstNameText.setText(NewUserInfo.getFirstName());
        lastNameText.setText(NewUserInfo.getLastName());
        emailAddressText.setText(NewUserInfo.getEmailAddress());
        dateText.setText(NewUserInfo.getDateOfBirth().toString());

        return view;
    }


}
