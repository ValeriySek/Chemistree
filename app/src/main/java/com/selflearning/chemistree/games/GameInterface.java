package com.selflearning.chemistree.games;

import com.selflearning.chemistree.MenuDialogFragment;

public interface GameInterface {
    void finishGame(long score);
    void observerMenuDialog(OnDialogFragmentShow dialogFragmentShow);
}
