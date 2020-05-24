package com.selflearning.chemistree.ui.gamespack;

import android.app.Activity;
import android.view.View;

import com.selflearning.chemistree.games.GameComponents;
import com.selflearning.chemistree.games.PreGameActivity;
import com.selflearning.chemistree.utilities.ActivityUtilities;

public class GamesRowType implements RowType {

    private String name;
    private Activity activity;
    private int typeFr;
    private int background;
    private GameComponents gameComponents;



    public GamesRowType(Activity activity, GameComponents gameComponents) {
        this.gameComponents = gameComponents;
        this.name = gameComponents.getNameOfGame();
        this.activity = activity;
        typeFr = gameComponents.getId();
        this.background = gameComponents.getBack();
    }


    public String getName() {
        return name;
    }


    public int getId() {
        return typeFr;
    }

    public int getBackground() {
        return background;
    }

    public View.OnClickListener getOnClickListener(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ActivityUtilities.getInstance().invokeNewActivity(activity, PreGameActivity.class, typeFr, false);
            }
        };
    }
}
