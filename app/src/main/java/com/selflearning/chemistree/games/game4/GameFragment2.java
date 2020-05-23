package com.selflearning.chemistree.games.game4;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.selflearning.chemistree.R;
import com.selflearning.chemistree.adapter.GameButtonsAdapter;
import com.selflearning.chemistree.games.BaseGameFragment;
import com.selflearning.chemistree.games.GameInterface;
import com.selflearning.chemistree.games.GameObserver;
import com.selflearning.chemistree.listeners.OnItemClickListener;

import java.util.List;

public class GameFragment2 extends BaseGameFragment {

    private static final int TYPE = 1;

    private static Context context;

    private TextView tvQuestion;
    private TextView tvAnswer;
    private TextView tvScore;
    private RecyclerView recyclerView;
    private MaterialButton mbSkip;
    private MaterialButton mbSubmit;
    private MaterialButton mbDelete;

    private GameButtonsAdapter adapter;
    private GameFragment2ViewModel viewModel;
    private int lives = 3;

    public static GameFragment2 getInstance(GameInterface gameInterface){
        gameI = gameInterface;
        return new GameFragment2();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game2, container, false);
        context = getActivity();
        gameName = "GameFragment2";
        viewModel = new GameFragment2ViewModel((Application) getContext().getApplicationContext());
        getLifecycle().addObserver(new GameObserver(getActivity()));

        tvQuestion = view.findViewById(R.id.tvGame3Question);
        recyclerView = view.findViewById(R.id.rvGame3);
        mbSkip = view.findViewById(R.id.mbGame3Skip);
        mbSubmit = view.findViewById(R.id.mbGame3Submit);
        mbDelete = view.findViewById(R.id.mbGame3Delete);
        tvAnswer = view.findViewById(R.id.tvGame3Answer);
        tvScore = view.findViewById(R.id.tvGame2Score);


        recyclerView.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
        adapter = new GameButtonsAdapter(context, 1);
        recyclerView.setAdapter(adapter);

        loadData();
        initListener();

        return view;
    }

    private void loadData(){
        viewModel.getMutableStringsRV().observe(getActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.setList(strings);
            }
        });
        viewModel.getMutableBooleansRV().observe(getActivity(), new Observer<List<Boolean>>() {
            @Override
            public void onChanged(List<Boolean> booleans) {
                adapter.setBooleans(booleans);
            }
        });
        viewModel.getStringQuestion().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvQuestion.setText(s);
            }
        });
        viewModel.getEditTextAnswer().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAnswer.setText(s);
            }
        });
        viewModel.isDeleteButtonEnable().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mbDelete.setEnabled(aBoolean);
            }
        });
        viewModel.getScore().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvScore.setText(s);
            }
        });
    }

    private void initListener(){
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                viewModel.changeEnable(position, adapter.getList().get(position));
            }
        });

        mbSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.loadData();
            }
        });

        mbDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteLast();
            }
        });

        mbSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvAnswer.getText().equals(viewModel.getFormula())){
                    score = viewModel.setScore();
                    Toast.makeText(getActivity(), "true", Toast.LENGTH_SHORT).show();
                    tvAnswer.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                }else {
                    increaseLives();
                    viewModel.setAllEnabled();
                    mbDelete.setEnabled(false);
                    mbSkip.setEnabled(false);
                    mbSubmit.setEnabled(false);
                    Toast.makeText(getActivity(), "false", Toast.LENGTH_SHORT).show();
                    tvAnswer.setText(viewModel.getFormula());
                    tvAnswer.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                }
                wait500ms();
            }
        });
    }

    private void increaseLives(){
        if(--lives == 0){
            isFinish = true;
        }
    }

    private void wait500ms(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvAnswer.setBackgroundResource(R.drawable.white_stroke_3dp);
                if(!isFinish){
                    viewModel.loadData();
                } else {
                    BaseGameFragment.finishGame();
                    return;
                }

                mbSkip.setEnabled(true);
                mbSubmit.setEnabled(true);
            }
        }, 500);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "GameFragment2 destroyed");
    }

}
