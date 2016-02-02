package com.android.uptick.uptick;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class InvestmentAnswersListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mTitleList;
    private ArrayList<String> mDescList;
    private ArrayList<RelativeLayout> mLayouts;
    private Integer mPageNum;

    public InvestmentAnswersListAdapter(Context context, ArrayList<String> titleList,
                                        ArrayList<String> descList, Integer pageNum) {
        mContext = context;
        mTitleList = titleList;
        mDescList = descList;
        mLayouts = new ArrayList<>();
        mPageNum = pageNum;
    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTitleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.answers_listview_item, parent, false);
        } else {
            rowView = convertView;
        }

        TextView ans_title_tv = (TextView) rowView.findViewById(R.id.answer_title);
        TextView ans_desc_tv = (TextView) rowView.findViewById(R.id.answer_detail);

        Typeface tf = Typeface.createFromAsset(GetStartedActivity.getInstance().getAssets(), "fonts/Roboto-Light.ttf");
        ans_title_tv.setText(mTitleList.get(position));
        ans_title_tv.setTypeface(tf);

        if (mDescList != null) {
            ans_desc_tv.setText(mDescList.get(position));
            ans_desc_tv.setTypeface(tf);
        } else {
            ans_desc_tv.setVisibility(View.INVISIBLE);
        }

        final RelativeLayout itemView = (RelativeLayout) rowView.findViewById(R.id.list_item_view);
        mLayouts.add(position, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnsetAllAnswers();
                v.setBackgroundColor(GetStartedActivity.getInstance().getResources().getColor(R.color.blue_trans));
                SetItemInCache(position);

                int currPosition = GetStartedActivity.getViewPager().getCurrentItem();
                if (currPosition < 7) {
                    GetStartedActivity.getViewPager().setCurrentItem(currPosition + 1, true);
                }
            }
        });


        if (position == mTitleList.size()-1)
            SetItemFromCache();
        return rowView;
    }

    private void UnsetAllAnswers() {
        for (RelativeLayout layout: mLayouts) {
            layout.setBackgroundColor(GetStartedActivity.getInstance().getResources().getColor(R.color.no_color));
        }
    }

    private void SetItemFromCache() {
        switch (mPageNum) {
            case 2:
                if (NewUserInfo.getInvestorType() != NewUserInfo.KindOfInvestor.NONE) {
                    mLayouts.get(NewUserInfo.getInvestorType().getValue()).
                            setBackgroundColor(GetStartedActivity.getInstance().getResources().getColor(R.color.blue_trans));
                }
                break;
            case 3:
                if (NewUserInfo.getEmpStatus() != NewUserInfo.Status.NONE) {
                    mLayouts.get(NewUserInfo.getEmpStatus().getValue()).
                            setBackgroundColor(GetStartedActivity.getInstance().getResources().getColor(R.color.blue_trans));
                }
                break;
            case 4:
                if (NewUserInfo.getYearIncome() != NewUserInfo.IncomeAmountRange.NONE) {
                    mLayouts.get(NewUserInfo.getYearIncome().getValue()).
                            setBackgroundColor(GetStartedActivity.getInstance().getResources().getColor(R.color.blue_trans));
                }
                break;
            case 5:
                if (NewUserInfo.getValue() != NewUserInfo.ValueAmountRange.NONE) {
                    mLayouts.get(NewUserInfo.getValue().getValue()).
                            setBackgroundColor(GetStartedActivity.getInstance().getResources().getColor(R.color.blue_trans));
                }
                break;
            case 6:
                if (NewUserInfo.getTimeline() != NewUserInfo.Period.NONE) {
                    mLayouts.get(NewUserInfo.getTimeline().getValue()).
                            setBackgroundColor(GetStartedActivity.getInstance().getResources().getColor(R.color.blue_trans));
                }
                break;
        }
    }

    private void SetItemInCache(Integer position) {
        switch (mPageNum) {
            case 2:
                NewUserInfo.setInvestorType(NewUserInfo.KindOfInvestor.getEnum(position));
                break;
            case 3:
                NewUserInfo.setEmpStatus(NewUserInfo.Status.getEnum(position));
                break;
            case 4:
                NewUserInfo.setYearIncome(NewUserInfo.IncomeAmountRange.getEnum(position));
                break;
            case 5:
                NewUserInfo.setValue(NewUserInfo.ValueAmountRange.getEnum(position));
                break;
            case 6:
                NewUserInfo.setTimeline(NewUserInfo.Period.getEnum(position));
                break;
        }
    }
}
