package com.selflearning.chemistree.games;

import androidx.fragment.app.Fragment;

import com.selflearning.chemistree.utilities.FirestoreUtilities;

public class BaseGameFragment extends Fragment {

    protected boolean isFinish = false;
    protected static GameInterface gameI;
    protected static long score;
    protected static String gameName;

    protected static void finishGame(){
        new FirestoreUtilities().saveScore(gameName, score);
        gameI.finishGame(score);
    }
}
