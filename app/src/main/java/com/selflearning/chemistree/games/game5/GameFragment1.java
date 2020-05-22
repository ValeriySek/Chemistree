package com.selflearning.chemistree.games.game5;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.games.GameScoreActivity;
import com.selflearning.chemistree.utilities.ActivityUtilities;

public class GameFragment1 extends Fragment {

    private static final int TYPE = 0;

    private TextView tvScore;
    ConstraintLayout constraintLayout;

    public static GameFragment1 getInstance(){
        GameFragment1 preGame = new GameFragment1();
        return preGame;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game1, container, false);
        tvScore = view.findViewById(R.id.tvScore);
        constraintLayout = view.findViewById(R.id.clFragment1);


        initListener();

        return view;
    }





    private void initListener(){
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtilities.getInstance().invokeNewActivity(getActivity(), GameScoreActivity.class, TYPE, true);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "Fragment destroyed");
    }
}
