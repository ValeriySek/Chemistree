package com.selflearning.chemistree.feature.f_statistic;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.selflearning.chemistree.R;
import com.selflearning.chemistree.utilities.FirestoreUtilities;
import com.selflearning.chemistree.views.MyLineChart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends Fragment {

    private TextView tvStat;
    private TextView tvStatScore;
    private MyLineChart lineChart;
    private LineDataSet dataSet1;
    private List<Entry> entries;
    private List<Integer> colors;
    Integer color;

    public StatisticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);

        tvStat = view.findViewById(R.id.tvStat);
        tvStatScore = view.findViewById(R.id.tvStatScore);
        lineChart = view.findViewById(R.id.lineChart);

        entries = new ArrayList<>();
        colors = new ArrayList<>();

        color = this.getResources().getColor(R.color.cBlueViolet);

        new FirestoreUtilities().getScores("GameFragment2").observe(getViewLifecycleOwner(), new Observer<List<Long>>() {
            @Override
            public void onChanged(List<Long> longs) {
                if(longs.size() < 11){
                    for(int i = 0; i < 11; i++){
                        if (i < 11 - longs.size()){
                            entries.add(new Entry(i, 0));
                        }else {
                            entries.add(new Entry(i, longs.get(i - (11 - longs.size()))));
                        }
                        colors.add(color);
                    }
                } else {
                    for (int i = 0; i < longs.size(); i++) {
                        colors.add(color);
                        entries.add(new Entry(i, longs.get(i)));
                    }
                }
                dataSet1 = new LineDataSet(entries, "score");
                setScore();

                long l = longs.size() == 0 ? 0 : Collections.max(longs);
                tvStatScore.setText(String.valueOf(l));
            }
        });

        return view;
    }

    private void setScore(){

        dataSet1.setDrawFilled(true);
        dataSet1.setCircleColors(colors);
        dataSet1.setValueTextSize(20);
        dataSet1.setValueTextColor(Color.WHITE);
        dataSet1.setCircleHoleColor(this.getResources().getColor(R.color.cBlueVioletDark));
//        dataSet1.setFillColor(color);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.gradient_white_transparent);
        dataSet1.setFillDrawable(drawable);
        dataSet1.setColor(color);
        dataSet1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSet1.setLineWidth(2f);

        LineData data = new LineData(dataSet1);

        lineChart.setData(data);
        lineChart.setVisibleXRangeMaximum(10);
        lineChart.moveViewToX(entries.size() - 1);
        lineChart.notifyDataSetChanged();
    }
}
