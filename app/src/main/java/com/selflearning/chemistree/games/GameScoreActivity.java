package com.selflearning.chemistree.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.constants.AppConstants;
import com.selflearning.chemistree.utilities.ActivityUtilities;

public class GameScoreActivity extends BaseGameActivity {

    private LinearLayout linearLayout;
    private TextView tvScore;
    private ViewGroup viewGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_score);

        linearLayout = findViewById(R.id.clPostGame);
        tvScore = findViewById(R.id.tvPostGameScore);
        viewGroup = findViewById(R.id.clPostGame);

        Intent intent = getIntent();
        int i = intent.getIntExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, -1);
        long score = intent.getLongExtra(AppConstants.BUNDLE_KEY_INDEX_SCORE, 0);

        tvScore.setText(String.valueOf(score));

        GameComponentsList components = new GameComponentsList();
        GameComponents gameComponents = components.getGameComponentsList().get(i);

        linearLayout.setBackground(getDrawable(gameComponents.getBack()));

        viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtilities.getInstance().invokeNewActivity(GameScoreActivity.this, PostGameActivity.class, -1, true);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
