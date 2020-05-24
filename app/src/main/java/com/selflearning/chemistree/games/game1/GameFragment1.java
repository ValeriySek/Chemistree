package com.selflearning.chemistree.games.game1;

import android.content.Context;
import android.os.Bundle;

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
public class GameFragment1 extends BaseGameFragment {

    private Context context;

    private GameButtonsAdapter adapter;
    private RecyclerView recyclerView;
    private Game_1_ViewModel game1ViewModel;

    private TextView tvQuestion;


    public static GameFragment1 getInstance(GameInterface gameInterface) {
        gameI = gameInterface;
        return new GameFragment1();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_game_1, container, false);

        tvQuestion = v.findViewById(R.id.game_1_textView);

        context = getContext();
        game1ViewModel = new Game_1_ViewModel(getActivity().getApplication());

        recyclerView = v.findViewById(R.id.rvGame1);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapter = new GameButtonsAdapter(context, 0);
        recyclerView.setAdapter(adapter);

        loadData();
        initListener();

        return v;
    }

    private void initListener(){
        adapter.setOnItemClickListener(position -> {
            if(position == game1ViewModel.rightAnswer) {
                Toast.makeText(context, "Right!", Toast.LENGTH_SHORT).show();
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
    }

    private void smthWait(){
        Handler handler = new Handler();
        handler.postDelayed(() -> game1ViewModel.loadData(), 1000);
    }
}
