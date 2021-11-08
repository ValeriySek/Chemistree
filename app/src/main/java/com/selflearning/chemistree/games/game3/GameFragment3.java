package com.selflearning.chemistree.games.game3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.adapter.GameButtonsAdapter;
import com.selflearning.chemistree.games.BaseGameFragment;
import com.selflearning.chemistree.games.GameInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment3 extends BaseGameFragment {

    private Context context;

    private RecyclerView recyclerView;
    private TextView tvQuestion;
    private TextView tvSkip;
    private TextView tvSubmit;
    private ImageView ivDelete;
    private TextView tvAnswer;

    private GameButtonsAdapter adapter;
    private Game_3_ViewModel game3ViewModel;

    public static GameFragment3 getInstance(GameInterface gameInterface) {
        gameI = gameInterface;
        return new GameFragment3();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_game_3, container, false);

        context = getContext();

        game3ViewModel = new Game_3_ViewModel(getActivity().getApplication());

        recyclerView = view.findViewById(R.id.game_2_fragment_rv);
        tvQuestion = view.findViewById(R.id.tvGame3Question);
        tvSkip = view.findViewById(R.id.tvGame3Skip);
        tvSubmit = view.findViewById(R.id.tvGame3Submit);
        ivDelete = view.findViewById(R.id.mbGame3Delete);
        tvAnswer = view.findViewById(R.id.tvGame3Answer);

        recyclerView.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
        adapter = new GameButtonsAdapter(1);
        recyclerView.setAdapter(adapter);

        loadData();
        initListener();

        return view;
    }

    private void loadData(){
        game3ViewModel.getAnswers().observe(getViewLifecycleOwner(), strings -> adapter.setList(strings));

        game3ViewModel.getBooleanLD().observe(getViewLifecycleOwner(), booleans -> adapter.setBooleans(booleans));

        game3ViewModel.getStringQuestion().observe(getViewLifecycleOwner(), s -> tvQuestion.setText(s));

        game3ViewModel.getStringAns().observe(getViewLifecycleOwner(), s -> tvAnswer.setText(s));
    }

    private void initListener(){
        adapter.setOnItemClickListener(position -> {

            game3ViewModel.chandgeEnable(position, adapter.getList().get(position));

            if(position == game3ViewModel.rightAnswer){
                Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
            }
        });

        ivDelete.setOnClickListener(v -> game3ViewModel.deleteLast());

        tvSkip.setOnClickListener(v -> game3ViewModel.loadData());
    }
}
