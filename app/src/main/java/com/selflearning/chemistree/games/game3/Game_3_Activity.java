package com.selflearning.chemistree.games.game3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.adapter.GameButtonsAdapter;
import com.selflearning.chemistree.listeners.OnItemClickListener;

import java.util.List;

public class Game_3_Activity extends AppCompatActivity {

    private Activity activity;
    private Context context;

    private RecyclerView recyclerView;
    private TextView tvQuestion;
    private TextView tvSkip;
    private TextView tvSubmit;
    private ImageView ivDelete;
    private TextView tvAnswer;

    private GameButtonsAdapter adapter;
    private Game_3_ViewModel game3ViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVar();
        initView();
        loadData();
        initListener();
    }

    private void initVar(){
        activity = Game_3_Activity.this;
        context = activity.getApplicationContext();
        game3ViewModel = ViewModelProviders.of(this).get(Game_3_ViewModel.class);
    }

    private void initView(){
        setContentView(R.layout.activity_game_3);
        recyclerView = findViewById(R.id.rvGame3);
        tvQuestion = findViewById(R.id.tvGame3Question);
        tvSkip = findViewById(R.id.tvGame3Skip);
        tvSubmit = findViewById(R.id.tvGame3Submit);
        ivDelete = findViewById(R.id.mbGame3Delete);
        tvAnswer = findViewById(R.id.tvGame3Answer);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        adapter = new GameButtonsAdapter(context, 1);
        recyclerView.setAdapter(adapter);
    }

    private void loadData(){
        game3ViewModel.getAnswers().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.setList(strings);
            }
        });
        game3ViewModel.getBooleanLD().observe(this, new Observer<List<Boolean>>() {
            @Override
            public void onChanged(List<Boolean> booleans) {
                adapter.setBooleans(booleans);
            }
        });
        game3ViewModel.getStringQuestion().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvQuestion.setText(s);
            }
        });
        game3ViewModel.getStringAns().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAnswer.setText(s);
            }
        });
    }

    private void initListener(){
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                game3ViewModel.chandgeEnable(position, adapter.getList().get(position));

                if(position == game3ViewModel.rightAnswer){
                    Toast.makeText(Game_3_Activity.this, "true", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Game_3_Activity.this, "false", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game3ViewModel.deleteLast();

            }
        });
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game3ViewModel.loadData();
            }
        });
    }

}
