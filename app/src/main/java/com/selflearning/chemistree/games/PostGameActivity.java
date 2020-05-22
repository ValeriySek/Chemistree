package com.selflearning.chemistree.games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

public class PostGameActivity extends AppCompatActivity {

    private MyLineChart lineChart;
    private TextView tvStatScore;
    private LineDataSet dataSet1;
    private List<Entry> entries;
    private List<Integer> colors;
    Integer color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_game);

        lineChart = findViewById(R.id.lineChart);
        tvStatScore = findViewById(R.id.tvStatScore);

        entries = new ArrayList<>();
        colors = new ArrayList<>();

        color = this.getResources().getColor(R.color.cBlueViolet);

        new FirestoreUtilities().getScores("GameFragment2").observe(this, new Observer<List<Long>>() {
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
                tvStatScore.setText(String.valueOf(
                        Collections.max(longs)
                ));
            }
        });
    }

    private void setScore(){

        dataSet1.setDrawFilled(true);
        dataSet1.setCircleColors(colors);
        dataSet1.setValueTextSize(20);
        dataSet1.setValueTextColor(Color.WHITE);
        dataSet1.setCircleHoleColor(this.getResources().getColor(R.color.cBlueVioletDark));
        dataSet1.setFillColor(color);
//        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.gradient_white_transparent);
//        dataSet1.setFillDrawable(drawable);
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
