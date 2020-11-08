package com.selflearning.chemistree.games.game1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.selflearning.chemistree.MenuDialogFragment;
import com.selflearning.chemistree.R;
import com.selflearning.chemistree.adapter.GameButtonsAdapter;
import com.selflearning.chemistree.games.BaseGameFragment;
import com.selflearning.chemistree.games.GameInterface;
import com.selflearning.chemistree.listeners.OnItemClickListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment1 extends BaseGameFragment {

    private Context context;

    private GameButtonsAdapter adapter;
    private RecyclerView recycler;
    private Game_1_ViewModel game1ViewModel;
    private CountDownTimer timer;

    private TextView tvQuestion;
    private TextView tvScore;
    private ProgressBar progressBar;
    private ViewGroup viewGroup;


    private final long MILLIS_IN_FUTURE = 15000;
    private long mTimeLeft = MILLIS_IN_FUTURE;
    private long countDownInterval = 10;
    private long currentTime;


    public static GameFragment1 getInstance(GameInterface gameInterface) {
        gameI = gameInterface;
        return new GameFragment1();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_game_1, container, false);

        gameName = "GameFragment1";

        tvQuestion = v.findViewById(R.id.game_1_textView);
        progressBar = v.findViewById(R.id.stats_progressbar);
        progressBar.setProgress(100);
        viewGroup = v.findViewById(R.id.game1ViewGroup);
        tvScore = v.findViewById(R.id.tvScore);

        context = getContext();
        game1ViewModel = new Game_1_ViewModel(getActivity().getApplication());

        recycler = v.findViewById(R.id.rvGame1);
        recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapter = new GameButtonsAdapter(0);
        recycler.setAdapter(adapter);

        loadData();
        initListener();
        downTimer();

        timer.start();

        return v;
    }

    private void initListener(){
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });


        adapter.setOnItemClickListener(position -> {
            if(position == game1ViewModel.rightAnswer) {
                Toast.makeText(context, "Right!", Toast.LENGTH_SHORT).show();
                game1ViewModel.increaseScore();
            } else{
                Toast.makeText(context, "No(((", Toast.LENGTH_SHORT).show();
            }
            smthWait();
        });
    }

    private void loadData(){
        game1ViewModel.getAnswers().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.setList(strings);
            }
        });
        game1ViewModel.getStringQuestion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvQuestion.setText(s);
            }
        });
        game1ViewModel.getMutableLiveDataScore().observe(getViewLifecycleOwner(), new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                score = aLong;
                tvScore.setText(String.valueOf(aLong));
            }
        });
    }

    private void smthWait(){
        Handler handler = new Handler();
        handler.postDelayed(() -> game1ViewModel.loadData(), 1000);
    }

    private void downTimer(){
        timer = new CountDownTimer(mTimeLeft, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = millisUntilFinished;
                updateProgressTimer();
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(0);
                finishGame();
            }
        };
    }

    private void updateProgressTimer(){
        long progress =  mTimeLeft * 100 / MILLIS_IN_FUTURE;
        progressBar.setProgress((int)progress);
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    public void isDialogFragmentShown(boolean isShow) {
        super.isDialogFragmentShown(isShow);
        if(isShow){
            timer.cancel();
        } else {
            timer.start();
        }
    }
}
