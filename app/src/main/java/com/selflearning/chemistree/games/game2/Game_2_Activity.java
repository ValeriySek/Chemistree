package com.selflearning.chemistree.games.game2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.adapter.GameButtonsAdapter;
import com.selflearning.chemistree.listeners.OnItemClickListener;

import java.util.List;

public class Game_2_Activity extends AppCompatActivity {

    private Activity activity;
    private Context context;

    private GameButtonsAdapter adapter;
    private Game_2_ViewModel game2ViewModel;

    private RecyclerView recyclerView;
    private TextView tvQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVar();
        initView();
        loadData();
        initListener();
    }

    private void initVar(){
        activity = Game_2_Activity.this;
        context = activity.getApplicationContext();
        game2ViewModel = ViewModelProviders.of(this).get(Game_2_ViewModel.class);
    }

    private void initView(){
        setContentView(R.layout.activity_game_2);
        tvQuestion = findViewById(R.id.tvGame2);
        recyclerView = findViewById(R.id.rvGame2);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapter = new GameButtonsAdapter(context, 0);
        recyclerView.setAdapter(adapter);
    }

    private void initListener(){
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(position == game2ViewModel.rightAnswer) {
                    Toast.makeText(context, "Right!", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(context, "No(((", Toast.LENGTH_SHORT).show();
                }
                smthWait();
            }
        });
    }

    private void loadData(){
        game2ViewModel.getAnswers().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.setList(strings);
            }
        });
        game2ViewModel.getStringQuestion().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvQuestion.setText(s);
            }
        });
    }

    private void smthWait(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                game2ViewModel.loadData();
            }
        }, 1000);
    }
}
