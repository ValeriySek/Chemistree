package com.selflearning.chemistree.games.game2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.adapter.GameButtonsAdapter;
import com.selflearning.chemistree.games.BaseGameFragment;
import com.selflearning.chemistree.games.GameInterface;
import com.selflearning.chemistree.listeners.OnItemClickListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment2 extends BaseGameFragment {


    private Activity activity;
    private Context context;

    private GameButtonsAdapter adapter;
    private Game_2_ViewModel game2ViewModel;

    private RecyclerView recyclerView;
    private TextView tvQuestion;

    public static GameFragment2 getInstance(GameInterface gameInterface) {
        gameI = gameInterface;
        return new GameFragment2();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_game_2, container, false);

        context = getActivity();

        game2ViewModel = new Game_2_ViewModel(getActivity().getApplication());

        tvQuestion = view.findViewById(R.id.tvGame2);
        recyclerView = view.findViewById(R.id.rvGame2);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapter = new GameButtonsAdapter(0);
        recyclerView.setAdapter(adapter);

        loadData();
        initListener();

        return view;
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
        game2ViewModel.getAnswers().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.setList(strings);
            }
        });
        game2ViewModel.getStringQuestion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvQuestion.setText(s);
            }
        });
    }

    private void smthWait(){
        Handler handler = new Handler();
        handler.postDelayed(() -> game2ViewModel.loadData(), 1000);
    }
}
