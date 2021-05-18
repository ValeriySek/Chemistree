package com.selflearning.chemistree.games.game4;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.selflearning.chemistree.R;
import com.selflearning.chemistree.adapter.GameButtonsAdapter;
import com.selflearning.chemistree.games.BaseGameFragment;
import com.selflearning.chemistree.games.GameInterface;
import com.selflearning.chemistree.games.GameObserver;
import com.selflearning.chemistree.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameFragment4 extends BaseGameFragment {

    private static final int TYPE = 1;

    private Context context;

    private TextView tvQuestion, tvAnswer, tvScore, tvQuestionNumber;
    private RecyclerView recyclerView;
    private MaterialButton mbSkip;
    private MaterialButton mbSubmit;
    private MaterialButton mbDelete;
    private ImageView ivHeart1, ivHeart2, ivHeart3;
    private List<ImageView> heartsPack = new ArrayList<>();

    private GameButtonsAdapter adapter;
    private GameFragment4ViewModel viewModel;
    private int lives = 3;
    private int numberOfQuestions = 10, question;

    public static GameFragment4 getInstance(GameInterface gameInterface){
        gameI = gameInterface;
        return new GameFragment4();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game2, container, false);
        context = getActivity();
        gameName = "GameFragment2";
        viewModel = new GameFragment4ViewModel(getActivity().getApplication());
        getLifecycle().addObserver(new GameObserver(getActivity()));

        tvQuestion = view.findViewById(R.id.tvGame3Question);
        recyclerView = view.findViewById(R.id.rvGame3);
        mbSkip = view.findViewById(R.id.mbGame3Skip);
        mbSubmit = view.findViewById(R.id.mbGame3Submit);
        mbDelete = view.findViewById(R.id.mbGame3Delete);
        tvAnswer = view.findViewById(R.id.tvGame3Answer);
        tvScore = view.findViewById(R.id.tvGame2Score);
        tvQuestionNumber = view.findViewById(R.id.tvQuestionNumber);
        ivHeart1 = view.findViewById(R.id.imageViewHeart1);
        ivHeart2 = view.findViewById(R.id.imageViewHeart2);
        ivHeart3 = view.findViewById(R.id.imageViewHeart3);

        heartsPack.add(ivHeart3);
        heartsPack.add(ivHeart2);
        heartsPack.add(ivHeart1);

        recyclerView.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
        adapter = new GameButtonsAdapter(1);
        recyclerView.setAdapter(adapter);

        tvQuestionNumber.setText(question + " / " + numberOfQuestions);

        loadData();
        initListener();

        return view;
    }

    private void loadData(){
        viewModel.getMutableStringsRV().observe(requireActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.setList(strings);
            }
        });
        viewModel.getMutableBooleansRV().observe(requireActivity(), new Observer<List<Boolean>>() {
            @Override
            public void onChanged(List<Boolean> booleans) {
                adapter.setBooleans(booleans);
            }
        });
        viewModel.getStringQuestion().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvQuestion.setText(s);
            }
        });
        viewModel.getEditTextAnswer().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAnswer.setText(s);
            }
        });
        viewModel.isDeleteButtonEnable().observe(requireActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mbDelete.setEnabled(aBoolean);
            }
        });
        viewModel.getScore().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvScore.setText(s);
            }
        });
    }

    private void initListener(){
        adapter.setOnItemClickListener(position -> viewModel.changeEnable(position, adapter.getList().get(position)));

        mbSkip.setOnClickListener(v -> viewModel.loadData());

        mbDelete.setOnClickListener(v -> viewModel.deleteLast());

        mbSubmit.setOnClickListener(v -> {
            if(tvAnswer.getText().equals(viewModel.getFormula())){
                onRightAnswer();
            }else {
                onWrongAnswer();
            }
            wait500ms();
        });
    }

    private void onRightAnswer(){
        tvQuestionNumber.setText(++question + " / " + numberOfQuestions);
        score = viewModel.setScore();
        Toast.makeText(getActivity(), "true", Toast.LENGTH_SHORT).show();
        tvAnswer.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
    }

    private void onWrongAnswer(){
        increaseLives();
        viewModel.setAllEnabled();
        mbDelete.setEnabled(false);
        mbSkip.setEnabled(false);
        mbSubmit.setEnabled(false);
        Toast.makeText(getActivity(), "false", Toast.LENGTH_SHORT).show();
        tvAnswer.setText(viewModel.getFormula());
        tvAnswer.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
    }

    private void increaseLives(){
        heartsPack.get(lives-1).setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_heart_filled_white_10));
        --lives;
    }

    private void wait500ms(){
        Handler handler = new Handler();
        handler.postDelayed(this::returnOriginalForm, 500);
    }

    private void returnOriginalForm(){
        if(lives == 0 || question == numberOfQuestions){
            isFinish = true;
        }
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

}
