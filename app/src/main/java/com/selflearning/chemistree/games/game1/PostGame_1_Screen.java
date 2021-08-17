package com.selflearning.chemistree.games.game1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.adapter.PostGameReviewAdapter;
import com.selflearning.chemistree.utilities.AppConstants;
import com.selflearning.chemistree.models.ReportModels;

import java.util.ArrayList;

public class PostGame_1_Screen extends AppCompatActivity {

    private Activity activity;
    private Context context;

    private TextView tvScore;
    private RecyclerView recyclerView;
    private int sc = 0;

    private ArrayList<ReportModels> reportModels;

    private PostGameReviewAdapter adapter = null;
    private long score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVar();
        initView();

        adapter = new PostGameReviewAdapter(reportModels);
        recyclerView.setAdapter(adapter);

    }

    private void initView(){
        setContentView(R.layout.activity_post_game_1__screen);
        tvScore = findViewById(R.id.tvPostGameScore);
        recyclerView = findViewById(R.id.rvPostGameAnswers);
        recyclerView.setNestedScrollingEnabled(false);

//        tvScore.setText(String.format("%s", score));
        setScore();
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

    }

    private void initVar(){
        activity = PostGame_1_Screen.this;
        context = activity.getApplicationContext();
        Intent intent = getIntent();
        if(intent != null){
            score = intent.getLongExtra(AppConstants.BUNDLE_KEY_INDEX_SCORE, 0);
            reportModels = intent.getParcelableArrayListExtra(AppConstants.BUNDLE_KEY_REPORT_MODEL);
        }

    }

    private void setScore(){
        final Handler handler = new Handler();
        final long startTime = SystemClock.uptimeMillis();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int i = sc;
                tvScore.setText(String.format("%s", i));
                if (i < (score-10)) sc+=10;
                handler.postDelayed(this, 1);
            }
        });

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                long timeInMs = sc;
//                if(timeInMs < score) sc = SystemClock.uptimeMillis() - startTime;
//                tvScore.setText(String.format("%s", timeInMs));
//                handler.postDelayed(this, 0);
//            }
//        }, 0);
        tvScore.setText(String.format("%s", score));
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity.finish();
    }
}
