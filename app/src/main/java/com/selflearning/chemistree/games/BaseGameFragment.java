package com.selflearning.chemistree.games;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.selflearning.chemistree.utilities.FirestoreUtilities;

public class BaseGameFragment extends Fragment implements OnDialogFragmentShow {

    protected boolean isFinish = false;
    protected static GameInterface gameI;
    protected static long score;
    protected static String gameName;


    @Override
    public void onResume() {
        super.onResume();
        gameI.observerMenuDialog(this);
    }

    protected static void finishGame(){
        new FirestoreUtilities().saveScore(gameName, score);
        gameI.finishGame(score);
    }

    @Override
    public void isDialogFragmentShown(boolean isShow) {
        Log.i("GameFr", "" + isShow);
    }

}
