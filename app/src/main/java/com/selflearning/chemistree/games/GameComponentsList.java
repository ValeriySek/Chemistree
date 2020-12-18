package com.selflearning.chemistree.games;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.f_games.gamespack.GamesRowType;

import java.util.ArrayList;
import java.util.List;

public class GameComponentsList {

    private List<GameComponents> gameComponentsList = new ArrayList<>();
    private List<GamesRowType> gamesRowTypeList = new ArrayList<>();


    public GameComponentsList() {
        fillGameComponentList();
        fillGamesRowTypeList();
    }

    private void fillGameComponentList(){
        gameComponentsList.add(new GameComponents(0, "First Game", R.drawable.image1));
        gameComponentsList.add(new GameComponents(1, "Second Game", R.drawable.image2));
        gameComponentsList.add(new GameComponents(2, "Threed Game", R.drawable.image3));
        gameComponentsList.add(new GameComponents(3, "Fourth Game", R.drawable.image1));
        gameComponentsList.add(new GameComponents(4, "Fifth Game", R.drawable.image2));
        gameComponentsList.add(new GameComponents(5, "Six Game", R.drawable.image3));
    }

    private void fillGamesRowTypeList(){
//        gamesRowTypeList.add(new GamesRowType("First Game", activity, PreGameActivity.class, 0, R.drawable.image1);
    }

    public List<GameComponents> getGameComponentsList() {
        return gameComponentsList;
    }
}
