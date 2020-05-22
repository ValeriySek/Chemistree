package com.selflearning.chemistree.games;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.selflearning.chemistree.R;
import com.selflearning.chemistree.constants.AppConstants;
import com.selflearning.chemistree.utilities.ActivityUtilities;

public class PreGameActivity extends BaseGameActivity {

    private ConstraintLayout constraintLayout;
    private TextView textView;
    private MaterialButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);
        constraintLayout = findViewById(R.id.preGameLayout);
        textView = findViewById(R.id.preGameText);
        button = findViewById(R.id.preGameButton);

        Intent intent = getIntent();
        final int type = intent.getIntExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, -1);

        GameComponentsList components = new GameComponentsList();
        GameComponents gameComponents = components.getGameComponentsList().get(type);

        Drawable drawable = getDrawable(gameComponents.getBack());
        constraintLayout.setBackground(drawable);
        textView.setText(gameComponents.getNameOfGame());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtilities.getInstance().invokeNewActivity(PreGameActivity.this, GameActivity.class,
                        type, true);
            }
        });

    }
}
