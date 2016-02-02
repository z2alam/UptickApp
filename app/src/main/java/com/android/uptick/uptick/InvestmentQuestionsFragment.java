package com.android.uptick.uptick;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class InvestmentQuestionsFragment extends Fragment {

    public InvestmentQuestionsFragment() {

    }

    public static InvestmentQuestionsFragment newInstance(int position) {
        InvestmentQuestionsFragment fragment = new InvestmentQuestionsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investment_details, container, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.toolbar_title);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        tvTitle.setTypeface(tf, Typeface.NORMAL);

        TextView tvQuestionTitle = (TextView) view.findViewById(R.id.question_title);
        tvQuestionTitle.setTypeface(tf);

        int position = getArguments().getInt("position");
        ListView answersListView;
        ArrayList<String> titles;
        ArrayList<String> details;
        InvestmentAnswersListAdapter adapter;

        switch (position) {
            case 2:
                tvQuestionTitle.setText(R.string.kind_of_investor);
                answersListView = (ListView) view.findViewById(R.id.answers_listview);

                titles = ConvertToList(getResources().getStringArray(R.array.kind_of_investors_ans_titles));
                details = ConvertToList(getResources().getStringArray(R.array.kind_of_investors_ans_desc));

                adapter = new InvestmentAnswersListAdapter(getActivity().getApplication().getApplicationContext(), titles, details, position);
                answersListView.setAdapter(adapter);
                break;
            case 3:
                tvQuestionTitle.setText(R.string.employment_status);
                answersListView = (ListView) view.findViewById(R.id.answers_listview);

                titles = ConvertToList(getResources().getStringArray(R.array.employment_status_ans));
                adapter = new InvestmentAnswersListAdapter(getActivity().getApplication().getApplicationContext(), titles, null, position);
                answersListView.setAdapter(adapter);
                break;
            case 4:
                tvQuestionTitle.setText(R.string.year_income);
                answersListView = (ListView) view.findViewById(R.id.answers_listview);

                titles = ConvertToList(getResources().getStringArray(R.array.year_income_ans));
                adapter = new InvestmentAnswersListAdapter(getActivity().getApplication().getApplicationContext(), titles, null, position);
                answersListView.setAdapter(adapter);
                break;
            case 5:
                tvQuestionTitle.setText(R.string.value_of_everything);
                answersListView = (ListView) view.findViewById(R.id.answers_listview);

                titles = ConvertToList(getResources().getStringArray(R.array.your_value_ans));
                adapter = new InvestmentAnswersListAdapter(getActivity().getApplication().getApplicationContext(), titles, null, position);
                answersListView.setAdapter(adapter);
                break;
            case 6:
                tvQuestionTitle.setText(R.string.investment_time);
                answersListView = (ListView) view.findViewById(R.id.answers_listview);

                titles = ConvertToList(getResources().getStringArray(R.array.usage_plan_ans));
                adapter = new InvestmentAnswersListAdapter(getActivity().getApplication().getApplicationContext(), titles, null, position);
                answersListView.setAdapter(adapter);
                break;
        }
        return view;
    }

    private ArrayList<String> ConvertToList(String[] array) {
        return new ArrayList(Arrays.asList(array));
    }
}
