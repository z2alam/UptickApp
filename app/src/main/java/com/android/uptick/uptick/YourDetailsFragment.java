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

public class YourDetailsFragment extends Fragment {

    private EditText mFirstNameET;
    private EditText mLastNameET;
    private EditText mEmailAddressET;
    private EditText mDateBirthET;
    private EditText mPasswordET;

    private ImageView mNextButton;
    private Date mDateOfBirth;

    DatePickerDialog.OnDateSetListener mDate;
    Calendar mCalendar;

    public YourDetailsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_your_details, container, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.toolbar_title);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        tvTitle.setTypeface(tf, Typeface.NORMAL);

        mFirstNameET = (EditText) view.findViewById(R.id.first_name_et);
        mLastNameET = (EditText) view.findViewById(R.id.last_name_et);
        mEmailAddressET = (EditText) view.findViewById(R.id.email_address_et);
        mDateBirthET = (EditText) view.findViewById(R.id.date_birth_et);
        mPasswordET = (EditText) view.findViewById(R.id.password_et);

        LoadTextValuesFromCache();

        mNextButton = (ImageView) view.findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidateTextFields()) {
                    hideSoftKeypad();
                    StoreTextValuesIntoCache();
                    GetStartedActivity.getViewPager().setCurrentItem(2, true);
                }
            }
        });

        mDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        mCalendar = Calendar.getInstance();
        setFocusChangeListeners();
        setTextStyle();

        return view;
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mDateBirthET.setText(sdf.format(mCalendar.getTime()));
    }

    private void setFocusChangeListeners() {
        mFirstNameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mFirstNameET.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large_edit_text));
                    showSoftKeypad(mFirstNameET);
                } else {
                    mFirstNameET.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.small_edit_text));
                }
            }
        });

        mLastNameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mLastNameET.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large_edit_text));
                    showSoftKeypad(mLastNameET);
                } else {
                    mLastNameET.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.small_edit_text));
                }
            }
        });

        mEmailAddressET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mEmailAddressET.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large_edit_text));
                    showSoftKeypad(mEmailAddressET);
                } else {
                    mEmailAddressET.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.small_edit_text));
                }
            }
        });

        mDateBirthET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mDateBirthET.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large_edit_text));
                    new DatePickerDialog(getActivity(), mDate, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                            mCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    hideSoftKeypad();
                } else {
                    mDateBirthET.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.small_edit_text));
                }
            }
        });

        mPasswordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mPasswordET.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large_edit_text));
                    showSoftKeypad(mPasswordET);
                } else {
                    mPasswordET.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.small_edit_text));
                }
            }
        });
    }

    public void setFocus() {
        showSoftKeypad(mFirstNameET);
    }

    private void showSoftKeypad(EditText view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    private void hideSoftKeypad() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    private void setTextStyle() {
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        mFirstNameET.setTypeface(tf, Typeface.NORMAL);
        mLastNameET.setTypeface(tf, Typeface.NORMAL);
        mEmailAddressET.setTypeface(tf, Typeface.NORMAL);
        mDateBirthET.setTypeface(tf, Typeface.NORMAL);
        mPasswordET.setTypeface(tf, Typeface.NORMAL);
    }

    private boolean ValidateTextFields() {
        String desc = "";
        boolean valid = true;

        if (mFirstNameET.length() == 0) {
            desc += "- First Name field is empty.\n";
            valid = false;
        }
        if (mLastNameET.length() == 0) {
            desc += "- Last Name field is empty.\n";
            valid = false;
        }
        if (mEmailAddressET.length() == 0) {
            desc += "- Email address field is empty.\n";
            valid = false;
        }

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            mDateOfBirth = format.parse(mDateBirthET.getText().toString());
        } catch (Exception e) {
            desc += "- Invalid date field.\n";
            valid = false;
        }

        if (mPasswordET.length() == 0) {
            desc += "- Password field is empty.\n";
            valid = false;
        }

        if (!valid) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Some Fields are Invalid")
                    .setMessage(desc)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return valid;
    }

    void LoadTextValuesFromCache() {
        mFirstNameET.setText((NewUserInfo.getFirstName() != null) ? NewUserInfo.getFirstName() : "");
        mLastNameET.setText((NewUserInfo.getLastName() != null) ? NewUserInfo.getLastName() : "");
        mEmailAddressET.setText((NewUserInfo.getEmailAddress() != null) ? NewUserInfo.getEmailAddress() : "");

        if (NewUserInfo.getDateOfBirth() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            String date = formatter.format(NewUserInfo.getDateOfBirth());
            mDateBirthET.setText(date.toString());
        }
        mPasswordET.setText((NewUserInfo.getPassword() != null) ? NewUserInfo.getPassword() : "");
    }

    void StoreTextValuesIntoCache() {
        NewUserInfo.setFirstName(mFirstNameET.getText().toString());
        NewUserInfo.setLastName(mLastNameET.getText().toString());
        NewUserInfo.setEmailAddress(mEmailAddressET.getText().toString());
        NewUserInfo.setDateOfBirth(mDateOfBirth);
        NewUserInfo.setPassword(mPasswordET.getText().toString());
    }
}
