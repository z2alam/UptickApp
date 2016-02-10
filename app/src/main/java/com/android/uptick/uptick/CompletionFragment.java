package com.android.uptick.uptick;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CompletionFragment extends Fragment {

    private TextView firstNameText;
    private TextView lastNameText;
    private TextView emailAddressText;
    private TextView dateText;
    private TextView kindOfInvestorText;
    private TextView empStatusText;
    private TextView yearIncomeText;
    private TextView personalValueText;
    private TextView investmentPlanText;


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
        firstNameText = (TextView) view.findViewById(R.id.first_name_text_tv);
        TextView lastNameLabel = (TextView) view.findViewById(R.id.last_name_label_tv);
        lastNameText = (TextView) view.findViewById(R.id.last_name_text_tv);
        TextView emailAddressLabel = (TextView) view.findViewById(R.id.email_address_label_tv);
        emailAddressText = (TextView) view.findViewById(R.id.email_address_text_tv);
        TextView dateLabel = (TextView) view.findViewById(R.id.date_label_tv);
        dateText = (TextView) view.findViewById(R.id.date_text_tv);
        TextView step2Label = (TextView) view.findViewById(R.id.step2_label);
        TextView kindOfInvestorLabel = (TextView) view.findViewById(R.id.kind_of_investor_label_tv);
        kindOfInvestorText = (TextView) view.findViewById(R.id.kind_of_investor_text_tv);
        TextView empStatusLabel = (TextView) view.findViewById(R.id.emp_status_label_tv);
        empStatusText = (TextView) view.findViewById(R.id.emp_status_text_tv);
        TextView yearIncomeLabel = (TextView) view.findViewById(R.id.year_income_label_tv);
        yearIncomeText = (TextView) view.findViewById(R.id.year_income_text_tv);
        TextView personalValueLabel = (TextView) view.findViewById(R.id.personal_value_label_tv);
        personalValueText = (TextView) view.findViewById(R.id.personal_value_text_tv);
        TextView investmentPlanLabel = (TextView) view.findViewById(R.id.plan_on_usage_label_tv);
        investmentPlanText = (TextView) view.findViewById(R.id.plan_on_usage_text_tv);

        TextView startTradingText = (TextView) view.findViewById(R.id.start_trading_tv);
        startTradingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        step1Label.setTypeface(tf, Typeface.BOLD);
        firstNameLabel.setTypeface(tf);
        firstNameText.setTypeface(tf);
        lastNameLabel.setTypeface(tf);
        lastNameText.setTypeface(tf);
        emailAddressLabel.setTypeface(tf);
        emailAddressText.setTypeface(tf);
        dateLabel.setTypeface(tf);
        dateText.setTypeface(tf);
        step2Label.setTypeface(tf, Typeface.BOLD);
        kindOfInvestorLabel.setTypeface(tf);
        kindOfInvestorText.setTypeface(tf);
        empStatusLabel.setTypeface(tf);
        empStatusText.setTypeface(tf);
        yearIncomeLabel.setTypeface(tf);
        yearIncomeText.setTypeface(tf);
        personalValueLabel.setTypeface(tf);
        personalValueText.setTypeface(tf);
        investmentPlanLabel.setTypeface(tf);
        investmentPlanText.setTypeface(tf);

        return view;
    }

    public void setTextFields() {
        firstNameText.setText(NewUserInfo.getFirstName());
        lastNameText.setText(NewUserInfo.getLastName());
        emailAddressText.setText(NewUserInfo.getEmailAddress());
        if (NewUserInfo.getDateOfBirth() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            String date = formatter.format(NewUserInfo.getDateOfBirth());
            dateText.setText(date.toString());
        }
        kindOfInvestorText.setText(NewUserInfo.getInvestorType().getString());
        empStatusText.setText(NewUserInfo.getEmpStatus().getString());
        yearIncomeText.setText(NewUserInfo.getYearIncome().getString());
        personalValueText.setText(NewUserInfo.getValue().getString());
        investmentPlanText.setText(NewUserInfo.getTimeline().getString());
    }


}
