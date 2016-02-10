package com.android.uptick.uptick;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchBox;
    private TextView mDollarSign;
    private TextView mDollarValue;
    private TextView mCentValue;
    private TextView mPrevClose;
    private TextView mCompanyName;
    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBox = (EditText)findViewById(R.id.search_box);
        mSearchBox.clearFocus();
        mSearchBox.setFocusable(true);
        mSearchBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSoftKeypad(mSearchBox);
                mSearchBox.setText("");
            }
        });

        mSearchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    new RetrieveStock().execute(mSearchBox.getText().toString());
                    new RetrieveStockHistory().execute(mSearchBox.getText().toString());
                }
                return false;
            }
        });
        setTextViews();
    }


    class RetrieveStock extends AsyncTask<String, String, Stock>  {
        @Override
        protected Stock doInBackground(String... data) {
            Stock stock = null;
            try {
                stock = YahooFinance.get(data[0]);
                stock.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stock;
        }

        @Override
        protected void onPostExecute(Stock s) {
            showStockPrice(s);
        }
    }

    class RetrieveStockHistory extends AsyncTask<String, String, Stock>  {
        @Override
        protected Stock doInBackground(String... data) {
            Stock stock = null;
            try {
                Calendar from = Calendar.getInstance();
                Calendar to = Calendar.getInstance();
                from.add(Calendar.DAY_OF_WEEK, -7);

                stock = YahooFinance.get(data[0], from, to, Interval.DAILY);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stock;
        }

        @Override
        protected void onPostExecute(Stock s) {
            if (s != null)
                setupChart(s);
        }
    }

    private void showSoftKeypad(EditText view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    private void hideSoftKeypad() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    private void setTextViews() {
        mDollarSign = (TextView) findViewById(R.id.dollar_sign);
        mDollarValue = (TextView) findViewById(R.id.dollar_amount);
        mCentValue = (TextView) findViewById(R.id.cent_amount);
        mPrevClose = (TextView) findViewById(R.id.prev_close);
        mCompanyName = (TextView) findViewById(R.id.company_name);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        mDollarSign.setTypeface(tf);
        mDollarValue.setTypeface(tf);
        mCentValue.setTypeface(tf);
        mPrevClose.setTypeface(tf);
        mCompanyName.setTypeface(tf);

        mDollarSign.setVisibility(View.INVISIBLE);
        mDollarValue.setVisibility(View.INVISIBLE);
        mCentValue.setVisibility(View.INVISIBLE);
        mPrevClose.setVisibility(View.INVISIBLE);
        mCompanyName.setVisibility(View.INVISIBLE);
    }

    protected static String getDecimalString(Double value) {
        value = value - value.intValue();
        value *= 100;

        Integer decimal = value.intValue();
        String result = ((Integer)(decimal / 10)).toString() + ((Integer)(decimal % 10)).toString();
        return result;
    }

    protected void setPrevCloseString(Stock value) {
        BigDecimal curr = value.getQuote().getPrice();
        BigDecimal prev = value.getQuote().getPreviousClose();

        boolean increment = (prev.compareTo(curr) < 0);
        Double inc_amount = Math.round((Math.abs(curr.doubleValue() - prev.doubleValue())) * 100.0) / 100.0;
        Double percentage = Math.round(((inc_amount/prev.doubleValue()) * 100) * 100.0) / 100.0;

        String prev_value;

        if (increment) {
            prev_value = "+$" + inc_amount.toString() + " (+" + percentage.toString() + "%)";
            mPrevClose.setTextColor(getResources().getColor(R.color.green2));
        } else {
            prev_value = "-$" + inc_amount.toString() + " (-" + percentage.toString() + "%)";
            mPrevClose.setTextColor(getResources().getColor(R.color.navy));
        }
        mPrevClose.setText(prev_value);
    }

    protected void showStockPrice(Stock price) {
        BigDecimal currPrice = price.getQuote().getPrice();
        Integer currDollar = currPrice.intValue();

        mDollarValue.setText(currDollar.toString());
        mCentValue.setText("." + getDecimalString(currPrice.doubleValue()));
        mCompanyName.setText(price.getName() + " (" + price.getSymbol() + ")");
        setPrevCloseString(price);

        mDollarSign.setVisibility(View.VISIBLE);
        mDollarValue.setVisibility(View.VISIBLE);
        mCentValue.setVisibility(View.VISIBLE);
        mPrevClose.setVisibility(View.VISIBLE);
        mCompanyName.setVisibility(View.VISIBLE);
    }

    protected void setupChart(Stock stock) {
        mChart = (LineChart) findViewById(R.id.chart1);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextColor(Color.WHITE);
        xAxis.setGridColor(Color.WHITE);
        xAxis.setAxisLineColor(Color.WHITE);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setStartAtZero(false);
        leftAxis.setTextColor(Color.WHITE);
        mChart.getAxisRight().setEnabled(false);

        // Add data
        setData(stock);
        mChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);
    }

    private void setData(Stock stock) {

        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Entry> yVals = new ArrayList<Entry>();

        try {
            List<HistoricalQuote> hist = stock.getHistory();

            for (int i = 0; i < hist.size(); i++) {
                xVals.add(i + "");
            }

            for (int i = 0; i < hist.size(); i++) {
                yVals.add(new Entry((hist.get(i).getClose().floatValue()), i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "Last 5 Days");
        set1.setColor(Color.WHITE);
        set1.setFillColor(Color.WHITE);

        set1.setLineWidth(1f);
        set1.setDrawCircleHole(false);
        set1.setDrawValues(false);
        set1.setDrawCircles(false);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setDrawingCacheBackgroundColor(Color.WHITE);
        mChart.setDescriptionColor(Color.WHITE);
        mChart.setGridBackgroundColor(Color.TRANSPARENT);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        GraphMarker mv = new GraphMarker(this, R.layout.custom_marker_view);
        mChart.setMarkerView(mv);

        mChart.setDescription("");
        mChart.setDrawGridBackground(false);
        mChart.setData(data);
    }
}
