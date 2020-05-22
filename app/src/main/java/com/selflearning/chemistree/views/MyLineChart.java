package com.selflearning.chemistree.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.Utils;

public class MyLineChart extends LineChart {


    public MyLineChart(Context context) {
        super(context);
    }

    public MyLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();

        mRenderer = new MyLineChartRender(this, mAnimator, mViewPortHandler);

        XAxis xAxis = getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setEnabled(false);
        setExtraRightOffset(75);
        setSet();
    }

    void setSet(){
        setDrawBorders(false);
        setDoubleTapToZoomEnabled(false);
        getXAxis().setDrawAxisLine(false);
        getXAxis().setDrawLabels(false);
        // remove axis
        YAxis leftAxis = getAxisLeft();
        leftAxis.setEnabled(false);
        YAxis rightAxis = getAxisRight();
        rightAxis.setEnabled(false);
        // hide legend
        Legend legend = getLegend();
        legend.setEnabled(false);
        Description description = new Description();
        description.setText("");
        setDescription(description);
        this.setVisibleXRange(7, 7);

//        lineChart.setDrawGridBackground(false);
        notifyDataSetChanged();
        invalidate();
    }

}
